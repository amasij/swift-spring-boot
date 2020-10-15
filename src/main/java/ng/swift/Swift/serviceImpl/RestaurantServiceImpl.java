package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.AddressRepository;
import ng.swift.Swift.repositories.GPSCoordinateRepository;
import ng.swift.Swift.repositories.RestaurantRepository;
import ng.swift.Swift.repositories.StateRepository;
import ng.swift.Swift.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Date;

@Named
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final GPSCoordinateRepository gpsCoordinateRepository;
    private final AddressRepository addressRepository;
    private final StateRepository stateRepository;

    @Transactional
    @Override
    public Restaurant createRestaurant(RestaurantCreationDto dto) {
        State state = stateRepository.findByCode(dto.getAddress().getStateCode()).orElseThrow(()->new ErrorResponse(HttpStatus.NOT_FOUND,"State not found"));
        GPSCoordinate savedCoordinate = null;
        if(dto.getAddress().getGpsCoordinate() != null){
            GPSCoordinate gpsCoordinate = new GPSCoordinate();
            gpsCoordinate.setLatitude(dto.getAddress().getGpsCoordinate().getLatitude());
            gpsCoordinate.setLongitude(dto.getAddress().getGpsCoordinate().getLongitude());
            gpsCoordinate.setStatus(EntityStatusConstant.ACTIVE);
            savedCoordinate = gpsCoordinateRepository.save(gpsCoordinate);
        }


        Address address = new Address();
        if(savedCoordinate != null){
            address.setGpsCoordinate(savedCoordinate);
        }
        address.setHouseNumber(dto.getAddress().getHouseNumber());
        address.setState(state);
        address.setStreetAddress(dto.getAddress().getStreetAddress());
        address.setStatus(EntityStatusConstant.ACTIVE);
        address.setType(AddressTypeConstant.CREATION);
        Address savedAddress =  addressRepository.save(address);

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(savedAddress);
        restaurant.setDateCreated(new Date());
        restaurant.setName(dto.getName());
        restaurant.setEmail(dto.getEmail());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        if(dto.getAlternatePhoneNumber() != null){
            restaurant.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());
        }
        restaurant.setStatus(EntityStatusConstant.ACTIVE);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findActiveById(id).orElseThrow(()-> new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid Restaurant"));
    }
}

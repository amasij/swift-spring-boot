package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.AddressRepository;
import ng.swift.Swift.repositories.GPSCoordinateRepository;
import ng.swift.Swift.repositories.RestaurantRepository;
import ng.swift.Swift.repositories.StateRepository;
import ng.swift.Swift.service.AddressService;
import ng.swift.Swift.service.GPSCoordinateService;
import ng.swift.Swift.service.RestaurantService;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Date;

@Named
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressService addressService;
    private final GPSCoordinateService gpsCoordinateService;
    private final UserService userService;

    @Transactional
    @Override
    public Restaurant createRestaurant(RestaurantCreationDto dto) {

        GPSCoordinate savedCoordinate = null;
        if (dto.getAddress().getGpsCoordinate() != null) {
            savedCoordinate = gpsCoordinateService.saveGps(dto.getAddress().getGpsCoordinate());
        }
        Address savedAddress = addressService.saveAddress(dto.getAddress(), savedCoordinate, AddressTypeConstant.CREATION);

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(savedAddress);
        restaurant.setDateCreated(new Date());
        restaurant.setName(dto.getName());
        restaurant.setEmail(dto.getEmail());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAlternatePhoneNumber() != null) {
            restaurant.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());
        }
        restaurant.setStatus(EntityStatusConstant.ACTIVE);
        User admin = userService.registerUser(dto.getAdmin());
        restaurant.setAdmin(admin);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findActiveById(id)
                .orElseThrow(() -> new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Restaurant"));
    }
}

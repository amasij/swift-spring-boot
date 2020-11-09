package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.AddressDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.AddressRepository;
import ng.swift.Swift.repositories.StateRepository;
import ng.swift.Swift.service.AddressService;
import org.springframework.http.HttpStatus;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final StateRepository stateRepository;

    @Override
    @Transactional
    public Address saveAddress(AddressDto dto, GPSCoordinate gps, AddressTypeConstant addressTypeConstant) {
        State state = stateRepository.findByCode(dto.getStateCode())
                .orElseThrow(()->new ErrorResponse(HttpStatus.BAD_REQUEST,"State not found"));
        Address address = new Address();
        if(gps != null){
            address.setGpsCoordinate(gps);
        }
        address.setStreetAddress(dto.getStreetAddress());
        address.setHouseNumber(dto.getHouseNumber());
        address.setState(state);
        address.setStatus(EntityStatusConstant.ACTIVE);
        address.setType(addressTypeConstant);
        return addressRepository.save(address);
    }
}

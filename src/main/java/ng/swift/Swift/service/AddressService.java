package ng.swift.Swift.service;

import ng.swift.Swift.dto.AddressDto;
import ng.swift.Swift.models.Address;
import ng.swift.Swift.models.AddressTypeConstant;
import ng.swift.Swift.models.GPSCoordinate;

public interface AddressService {
    Address saveAddress(AddressDto dto, GPSCoordinate gps, AddressTypeConstant addressTypeConstant);
}

package ng.swift.Swift.service;

import ng.swift.Swift.dto.GPSCoordinateDto;
import ng.swift.Swift.models.GPSCoordinate;

public interface GPSCoordinateService {
    GPSCoordinate saveGps(GPSCoordinateDto dto);
}

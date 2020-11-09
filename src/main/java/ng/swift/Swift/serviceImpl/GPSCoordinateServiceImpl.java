package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.GPSCoordinateDto;
import ng.swift.Swift.models.EntityStatusConstant;
import ng.swift.Swift.models.GPSCoordinate;
import ng.swift.Swift.repositories.GPSCoordinateRepository;
import ng.swift.Swift.service.GPSCoordinateService;

import javax.inject.Named;
import javax.transaction.Transactional;
@Named
@RequiredArgsConstructor
public class GPSCoordinateServiceImpl implements GPSCoordinateService {
    private final GPSCoordinateRepository gpsCoordinateRepository;

    @Override
    @Transactional
    public GPSCoordinate saveGps(GPSCoordinateDto dto) {
        GPSCoordinate gpsCoordinate = new GPSCoordinate();
        gpsCoordinate.setLongitude(dto.getLongitude());
        gpsCoordinate.setLatitude(dto.getLatitude());
        gpsCoordinate.setStatus(EntityStatusConstant.ACTIVE);
        return gpsCoordinateRepository.save(gpsCoordinate);
    }
}

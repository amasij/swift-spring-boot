package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.models.RestaurantOrderItem;
import ng.swift.Swift.models.Rider;
import ng.swift.Swift.repositories.RiderRepository;
import ng.swift.Swift.service.RiderService;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final RiderRepository riderRepository;

    @Override
    public Rider findRider(RestaurantOrderItem orderItem) {
        return null;
    }
}

package ng.swift.Swift.service;

import ng.swift.Swift.models.RestaurantOrderItem;
import ng.swift.Swift.models.Rider;

public interface RiderService {
    Rider findRider(RestaurantOrderItem orderItem);
}

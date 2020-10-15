package ng.swift.Swift.service;

import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.models.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreationDto dto);
    Restaurant getRestaurant(Long id);
}

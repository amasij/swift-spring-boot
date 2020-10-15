package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.models.Restaurant;
import ng.swift.Swift.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/register")
    public Restaurant createRestaurant(@Valid @RequestBody RestaurantCreationDto dto){
        return restaurantService.createRestaurant(dto);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurant(id));
    }
}

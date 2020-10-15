package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.MealCreationDto;
import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.Restaurant;
import ng.swift.Swift.models.User;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.RestaurantRepository;
import ng.swift.Swift.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealController {
    private final RestaurantRepository restaurantRepository;
    private final MealCategoryRepository mealCategoryRepository;
    private final MealService mealService;

    @PostMapping("/create/{id:\\d+}")
    public Meal createMeal(@PathVariable("id") Long restaurantId, @Valid @RequestBody MealCreationDto dto){
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(()-> new ErrorResponse(HttpStatus.NOT_FOUND,"Restaurant not found"));

        List<MealCategory> categories = mealCategoryRepository.findActiveByIds(dto.getMealCategoryIds());
        if(categories.isEmpty()){
            throw new ErrorResponse(HttpStatus.NOT_FOUND,"No meal category found");
        }

        return mealService.addMeal(restaurant, categories, dto);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Meal> getMeal(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mealService.getMeal(id));
    }

}

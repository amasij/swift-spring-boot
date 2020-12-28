package ng.swift.Swift.controllers;

import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.MealCreationDto;
import ng.swift.Swift.dto.RestaurantCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.filters.MealFilter;
import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.Restaurant;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.MealPojo;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.RestaurantRepository;
import ng.swift.Swift.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealController {
    private final RestaurantRepository restaurantRepository;
    private final MealCategoryRepository mealCategoryRepository;
    private final MealService mealService;

    @PostMapping("/create/{id:\\d+}")
    public MealPojo createMeal(@PathVariable("id") Long restaurantId, @Valid @RequestBody MealCreationDto dto) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ErrorResponse(HttpStatus.BAD_REQUEST, "Restaurant not found"));

        List<MealCategory> categories = mealCategoryRepository.findActiveByIds(dto.getMealCategoryIds());
        if (categories.isEmpty()) {
            throw new ErrorResponse(HttpStatus.BAD_REQUEST, "No meal category found");
        }

        return MealPojo.from(mealService.addMeal(restaurant, categories, dto));
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Meal> getMeal(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mealService.getMeal(id));
    }

    @GetMapping("/user-meals")
    public ResponseEntity<QueryResults<MealPojo>> getUserMeals(@RequestParam("userId") @NotNull Long userId, @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset) {
        MealFilter filter = new MealFilter();
        filter.setLimit(limit);
        filter.setOffset(offset);
        filter.setUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(mealService.getUserMeal(filter));
    }

}

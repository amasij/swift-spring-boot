package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.MealCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.MealRepository;
import ng.swift.Swift.repositories.RestaurantMealCategoryRepository;
import ng.swift.Swift.repositories.RestaurantRepository;
import ng.swift.Swift.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final RestaurantMealCategoryRepository restaurantMealCategoryRepository;
    private final MealRepository mealRepository;
    private final MealCategoryRepository mealCategoryRepository;

    @Transactional
    @Override
    public Meal addMeal(Restaurant restaurant, List<MealCategory> mealCategories , MealCreationDto dto) {
        Meal meal = new Meal();
        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setStatus(EntityStatusConstant.ACTIVE);
        meal.setDateCreated(new Date());
        meal.setRestaurant(restaurant);
        Meal savedMeal = mealRepository.save(meal);

        List<RestaurantMealCategory> restaurantMealCategories = new ArrayList<>();

        mealCategories.forEach(mealCategory -> {
            RestaurantMealCategory restaurantMealCategory = new RestaurantMealCategory();
            restaurantMealCategory.setMeal(savedMeal);
            restaurantMealCategory.setMealCategory(mealCategory);
            restaurantMealCategory.setStatus(EntityStatusConstant.ACTIVE);
            restaurantMealCategories.add(restaurantMealCategory);
        });
        restaurantMealCategoryRepository.saveAll(restaurantMealCategories);

        return savedMeal;
    }

    @Override
    public Meal getMeal(Long id) {
        return mealRepository.findActiveById(id).orElseThrow(()->new ErrorResponse(HttpStatus.NOT_FOUND,"Meal not found"));
    }
}

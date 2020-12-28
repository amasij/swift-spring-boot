package ng.swift.Swift.service;

import com.querydsl.core.QueryResults;
import ng.swift.Swift.dto.MealCreationDto;
import ng.swift.Swift.filters.MealFilter;
import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.Restaurant;
import ng.swift.Swift.pojo.MealPojo;

import java.util.List;

public interface MealService {
    Meal addMeal(Restaurant restaurant, List<MealCategory> mealCategories, MealCreationDto dto);
    Meal getMeal(Long id);
    QueryResults<MealPojo> getUserMeal(MealFilter filter);

}

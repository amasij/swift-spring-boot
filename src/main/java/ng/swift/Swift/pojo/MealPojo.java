package ng.swift.Swift.pojo;

import lombok.Getter;
import lombok.Setter;
import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MealPojo {
    private Long image;
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long restaurantId;

    public static MealPojo from(Meal meal){
        return getMealPojo(meal);
    }

    public static List<MealPojo> from(List<Meal> meals){
       return meals.stream().map(MealPojo::getMealPojo).collect(Collectors.toList());
    }

    private static MealPojo getMealPojo(Meal meal) {
        MealPojo pojo = new MealPojo();
        pojo.setId(meal.getId());
        pojo.setDescription(meal.getDescription());
        pojo.setName(meal.getName());
        pojo.setRestaurantId(meal.getRestaurant().getId());
        pojo.setPrice(meal.getPrice());
        pojo.setImage(meal.getPhoto().getId());
        return pojo;
    }
}

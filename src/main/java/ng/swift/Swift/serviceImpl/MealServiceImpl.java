package ng.swift.Swift.serviceImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.MealCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.filters.MealFilter;
import ng.swift.Swift.models.*;
import ng.swift.Swift.pojo.MealPojo;
import ng.swift.Swift.repositories.*;
import ng.swift.Swift.service.ImageFileService;
import ng.swift.Swift.service.MealService;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final RestaurantMealCategoryRepository restaurantMealCategoryRepository;
    private final MealRepository mealRepository;
    private final ImageFileService imageFileService;
    private final AppRepository appRepository;
    private final UserService userService;
    private final PreferenceRepository preferenceRepository;

    @Transactional
    @Override
    public Meal addMeal(Restaurant restaurant, List<MealCategory> mealCategories, MealCreationDto dto) {
        Meal meal = new Meal();
        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setStatus(EntityStatusConstant.ACTIVE);
        meal.setDateCreated(new Date());
        meal.setRestaurant(restaurant);
        meal.setDescription(dto.getDescription());
        meal.setPhoto(imageFileService.getImageFile(dto.getImageId()).orElseThrow(() -> new ErrorResponse(HttpStatus.BAD_REQUEST, "Image not found")));
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
        return mealRepository.findActiveById(id).orElseThrow(() -> new ErrorResponse(HttpStatus.BAD_REQUEST, "Meal not found"));
    }

    @Override
    public QueryResults<MealPojo> getUserMeal(MealFilter filter) {

        User user = userService.getUser(filter.getUserId());
        List<MealCategory> mealCategories = preferenceRepository.getActivePreferencesByUser(user)
                .stream().map(Preference::getMealCategory)
                .collect(Collectors.toList());

        JPAQuery<RestaurantMealCategory> jpaQuery = appRepository.startJPAQuery(QRestaurantMealCategory.restaurantMealCategory)
                .innerJoin(QRestaurantMealCategory.restaurantMealCategory.meal).fetchJoin()
                .limit(filter.getLimit().orElse(15))
                .offset(filter.getOffset().orElse(0));

        if (!mealCategories.isEmpty()) {
            jpaQuery.where(QRestaurantMealCategory.restaurantMealCategory.mealCategory.in(mealCategories));
        }

        QueryResults<RestaurantMealCategory> fetchedResults = jpaQuery.fetchResults();
        List<Meal> userMeals = fetchedResults.getResults().stream().map(RestaurantMealCategory::getMeal).distinct().collect(Collectors.toList());

        return new QueryResults<>(MealPojo.from(userMeals), fetchedResults.getLimit(), fetchedResults.getOffset(), fetchedResults.getTotal());

    }
}

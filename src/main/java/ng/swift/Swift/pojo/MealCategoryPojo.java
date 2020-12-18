package ng.swift.Swift.pojo;

import lombok.Getter;
import lombok.Setter;
import ng.swift.Swift.models.MealCategory;

@Getter
@Setter
public class MealCategoryPojo {
    private String name;
    private String code;

    public static  MealCategoryPojo from(MealCategory mealCategory){
        MealCategoryPojo pojo = new MealCategoryPojo();
        pojo.setCode(mealCategory.getCode());
        pojo.setName(mealCategory.getName());
        return pojo;
    }
}

package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="RestaurantMealCategory")
public class RestaurantMealCategory {
    private @Id @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @ManyToOne
    @JoinColumn(name = "meal", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "mealCategory", nullable = false)
    private MealCategory mealCategory;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;
}

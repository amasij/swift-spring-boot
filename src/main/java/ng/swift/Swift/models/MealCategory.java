package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="MealCategories")
public class MealCategory {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

}

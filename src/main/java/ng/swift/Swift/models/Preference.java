package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Preference")
public class Preference {

    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "mealCategory", nullable = false)
    private MealCategory mealCategory;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

}

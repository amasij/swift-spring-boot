package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name="Meal")
public class Meal {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false, unique = true)
    private String name;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;
}

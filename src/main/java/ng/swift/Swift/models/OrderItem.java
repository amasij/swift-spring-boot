package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="OrderItems")
public class OrderItem {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false)
    private Date dateCreated;

    @Column(nullable=false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "meal",nullable = false)
    private Meal meal;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;
}

package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Delivery")
public class Delivery {

    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;


    @OneToOne
    @JoinColumn(name = "rider", nullable = false)
    private Rider rider;

    @OneToOne
    @JoinColumn(name = "restaurantOrderItem", nullable = false)
    private RestaurantOrderItem restaurantOrderItem;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatusConstant deliveryStatus;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;
}

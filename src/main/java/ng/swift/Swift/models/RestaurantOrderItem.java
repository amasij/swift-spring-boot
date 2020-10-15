package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="RestaurantOrderItems")
public class RestaurantOrderItem {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "orderItem", nullable = false)
    private OrderItem orderItem;

    @Column(nullable=false)
    private Date dateCreated;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OrderStatusConstant orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;
}

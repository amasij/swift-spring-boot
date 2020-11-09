package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Rider")
public class Rider {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @OneToOne
    @JoinColumn(name = "details", nullable = false)
    private User details;

    @OneToOne
    @JoinColumn(name = "photo")
    private ImageFile photo;

    @ManyToOne
    @JoinColumn(name = "deliveryCompany", nullable = false)
    private DeliveryCompany deliveryCompany;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EngagementStatusConstant engagementStatus;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;
}

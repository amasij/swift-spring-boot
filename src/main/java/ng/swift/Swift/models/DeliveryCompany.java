package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "DeliveryCompany")
public class DeliveryCompany {

    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "admin", nullable = false)
    private User admin;

    @OneToOne
    @JoinColumn(name = "logo")
    private ImageFile logo;


    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;


}

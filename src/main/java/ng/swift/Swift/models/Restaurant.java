package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="Restaurant")
public class Restaurant {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false,unique=true)
    private String name;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false,unique=true)
    private String phoneNumber;

    @Column(unique=true)
    private String alternatePhoneNumber;

    @Column(nullable=false)
    private Date dateCreated;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "admin", nullable = false)
    private User admin;

    @OneToOne
    @JoinColumn(name = "photo")
    private ImageFile photo;
}

package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name="users")
public class User {
    private @Id
    @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String phoneNumber;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

    @Column(nullable=false)
    private Date dateCreated;

    private Date lastUpdated;

    @OneToOne
    @JoinColumn(name = "photo")
    private ImageFile photo;


}

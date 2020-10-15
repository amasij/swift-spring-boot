package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Address")
public class Address {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @ManyToOne
    @JoinColumn(name = "gpsCordinate", nullable = false)
    private GPSCoordinate gpsCoordinate;

    @ManyToOne
    @JoinColumn(name = "state", nullable = false)
    private State state;

    private String streetAddress;
    private String houseNumber;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private AddressTypeConstant type;
}

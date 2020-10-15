package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressDto {
    @NotBlank
    private String streetAddress;
    private String houseNumber;
    @NotBlank
    private String stateCode;
    private GPSCoordinateDto gpsCoordinate;

}

package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantCreationDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    private String alternatePhoneNumber;
    @NotNull
    private AddressDto address;

    @NotNull
    @Valid
    private UserCreationDto admin;
}

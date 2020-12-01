package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;
import ng.swift.Swift.annotations.PhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreationDto {

    @NotBlank(message = "EMAIL_IS_REQUIRED")
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NotBlank(message = "NAME_IS_REQUIRED")
    private String name;

    @PhoneNumber
    @NotBlank(message = "PHONE_NUMBER_IS_REQUIRED")
    private String phoneNumber;

    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    private String password;

}

package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDto {
    @NotNull
    @NotBlank
    private String identifier;
    @NotNull
    @NotBlank
    private String password;
}

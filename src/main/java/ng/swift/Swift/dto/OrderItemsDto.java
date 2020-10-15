package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OrderItemsDto {
    @NotBlank
    private Long mealId;
    @NotNull
    @Min(0)
    private Integer quantity;
}

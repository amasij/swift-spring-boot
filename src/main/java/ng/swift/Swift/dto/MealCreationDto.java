package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MealCreationDto {
    @NotBlank
    private String name;
    @NotBlank
    private BigDecimal price;
    @Size(min = 1)
    private List<Long> mealCategoryIds;
    @NotBlank
    private String description;
}

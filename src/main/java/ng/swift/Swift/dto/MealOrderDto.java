package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class MealOrderDto {
    private AddressDto address;
    @Size(min = 1)
    private List<OrderItemsDto> orderItems;
}

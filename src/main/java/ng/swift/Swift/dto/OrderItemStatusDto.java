package ng.swift.Swift.dto;

import lombok.Getter;
import lombok.Setter;
import ng.swift.Swift.models.OrderStatusConstant;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemStatusDto {
    @NotNull
    private OrderStatusConstant status;
    @NotNull
    private Long id;
}

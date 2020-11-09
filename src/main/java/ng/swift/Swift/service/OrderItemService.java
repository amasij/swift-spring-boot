package ng.swift.Swift.service;

import ng.swift.Swift.dto.MealOrderDto;
import ng.swift.Swift.dto.OrderItemStatusDto;
import ng.swift.Swift.models.OrderItem;
import ng.swift.Swift.models.User;

public interface OrderItemService {
    String placeOrder(User user, MealOrderDto dto);
    String setOrderStatus(OrderItemStatusDto dto);
}

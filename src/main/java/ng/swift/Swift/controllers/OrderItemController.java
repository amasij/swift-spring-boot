package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.MealOrderDto;
import ng.swift.Swift.dto.OrderItemStatusDto;
import ng.swift.Swift.models.OrderStatusConstant;
import ng.swift.Swift.models.User;
import ng.swift.Swift.service.OrderItemService;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderItemController {
    private final UserService userService;
    private final OrderItemService orderItemService;


    @PostMapping("/place/{id:\\d+}")
    public ResponseEntity<String> placeOrder(@PathVariable("id") Long userId, @Valid @RequestBody MealOrderDto dto){
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.placeOrder(user, dto));
    }

//    @PostMapping("/status")
//    public ResponseEntity<?> setOrderStatus(@Valid @RequestBody OrderItemStatusDto dto){
//        User user = userService.getUser(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.placeOrder(user, dto));
//    }
}

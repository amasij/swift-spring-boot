package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.*;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.*;
import ng.swift.Swift.service.AddressService;
import ng.swift.Swift.service.GPSCoordinateService;
import ng.swift.Swift.service.OrderItemService;
import ng.swift.Swift.service.RiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final MealRepository mealRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantOrderItemRepository restaurantOrderItemRepository;
    private final AddressService addressService;
    private final GPSCoordinateService gpsCoordinateService;
    private final RiderService riderService;
    private final DeliveryRepository deliveryRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public String placeOrder(User user, MealOrderDto dto) {
        Date orderDate = Date.from(Instant.now());

        List<Long> mealIds = dto.getOrderItems().stream()
                .map(OrderItemsDto::getMealId).collect(Collectors.toList());
        List<Meal> meals = mealRepository.findActiveByIds(mealIds);

        if(meals.isEmpty()){
            throw new ErrorResponse(HttpStatus.BAD_REQUEST,"No meals selected");
        }
        dto.getOrderItems().forEach(orderItemDto ->{

            Meal meal = meals.stream().filter(x-> x.getId().equals(orderItemDto.getMealId())).findFirst().
                    orElseThrow(()->new ErrorResponse(HttpStatus.BAD_REQUEST,"Meal not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setDateCreated(orderDate);
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setMeal(meal);
            orderItem.setStatus(EntityStatusConstant.ACTIVE);
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            Address address = createAddress(dto.getAddress());
            createRestaurantOrderItem(user, meal.getRestaurant(), savedOrderItem, address, orderDate);
        });

        return "Order Placed";
    }


    private Address createAddress(AddressDto addressDto){
        GPSCoordinate gpsCoordinate = gpsCoordinateService.saveGps(addressDto.getGpsCoordinate());
        return addressService.saveAddress(addressDto,gpsCoordinate,AddressTypeConstant.DELIVERY);
    }

    private void createRestaurantOrderItem(User user, Restaurant restaurant, OrderItem orderItem, Address address, Date orderDate ){
        RestaurantOrderItem restaurantOrderItem = new RestaurantOrderItem();
        restaurantOrderItem.setAddress(address);
        restaurantOrderItem.setDateCreated(orderDate);
        restaurantOrderItem.setOrderItem(orderItem);
        restaurantOrderItem.setRestaurant(restaurant);
        restaurantOrderItem.setUser(user);
        restaurantOrderItem.setStatus(EntityStatusConstant.ACTIVE);
        restaurantOrderItem.setOrderStatus(OrderStatusConstant.NOT_STARTED);
        restaurantOrderItemRepository.save(restaurantOrderItem);
    }

    @Transactional
    @Override
    public Delivery setOrderStatus(OrderItemStatusDto dto) {
        RestaurantOrderItem restaurantOrderItem = restaurantOrderItemRepository.findActiveById(dto.getId())
                .orElseThrow(()-> new ErrorResponse(HttpStatus.BAD_REQUEST,"Order not found"));
        restaurantOrderItem.setOrderStatus(dto.getStatus());

        if(restaurantOrderItem.getOrderStatus() == OrderStatusConstant.CANCELLED){
            restaurantOrderItem.setStatus(EntityStatusConstant.DEACTIVATED);
        }else if(restaurantOrderItem.getOrderStatus() == OrderStatusConstant.PREPARED){
            Rider rider = riderService.findRider(restaurantOrderItem);
                if(rider != null){
//                    restaurantOrderItem.setOrderStatus(OrderStatusConstant.AWAITING_DELIVERY);
                    Delivery delivery = new Delivery();
                    delivery.setDeliveryStatus(DeliveryStatusConstant.AWAITING_PICKUP);
                    delivery.setRestaurantOrderItem(restaurantOrderItem);
                    delivery.setRider(rider);
                    restaurantOrderItemRepository.save(restaurantOrderItem);
                    return deliveryRepository.save(delivery);
                }
        }
        restaurantOrderItemRepository.save(restaurantOrderItem);
        return null;
    }

}

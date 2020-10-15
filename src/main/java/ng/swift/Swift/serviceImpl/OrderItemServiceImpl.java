package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.AddressDto;
import ng.swift.Swift.dto.GPSCoordinateDto;
import ng.swift.Swift.dto.MealOrderDto;
import ng.swift.Swift.dto.OrderItemsDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.repositories.*;
import ng.swift.Swift.service.OrderItemService;
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
    private final StateRepository stateRepository;
    private final GPSCoordinateRepository gpsCoordinateRepository;
    private final AddressRepository addressRepository;
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
        State state = stateRepository.findByCode(addressDto.getStateCode()).orElseThrow(()->new ErrorResponse(HttpStatus.NOT_FOUND,"State not found"));
        Address address = new Address();
        address.setType(AddressTypeConstant.DELIVERY);
        address.setStatus(EntityStatusConstant.ACTIVE);
        address.setState(state);
        address.setStreetAddress(addressDto.getStreetAddress());
        if(addressDto.getHouseNumber() != null){
            address.setHouseNumber(addressDto.getHouseNumber());
        }
        if(addressDto.getGpsCoordinate() != null){
            GPSCoordinate gpsCoordinate = setGPSCoordinate(addressDto.getGpsCoordinate());
            address.setGpsCoordinate(gpsCoordinate);
        }
        return addressRepository.save(address);


    }

    private GPSCoordinate setGPSCoordinate(GPSCoordinateDto gpsCoordinateDto){
        GPSCoordinate gpsCoordinate = new GPSCoordinate();
        gpsCoordinate.setStatus(EntityStatusConstant.ACTIVE);
        gpsCoordinate.setLatitude(gpsCoordinateDto.getLatitude());
        gpsCoordinate.setLongitude(gpsCoordinateDto.getLongitude());
        return gpsCoordinateRepository.save(gpsCoordinate);
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
}
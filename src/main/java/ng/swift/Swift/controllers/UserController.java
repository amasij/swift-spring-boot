package ng.swift.Swift.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.LoginDto;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.dto.UserPreferenceDto;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.MealCategoryPojo;
import ng.swift.Swift.pojo.UserPojo;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Call this API to register a new user")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "New user has been registered"))
    public UserPojo registerUser(@Valid @RequestBody UserCreationDto dto) {
        User user = userService.registerUser(dto);
        return UserPojo.from(user);
    }

    @PostMapping("/login")
    public UserPojo login(@Valid @RequestBody LoginDto dto) {
        User user = userService.loginUser(dto);
        return UserPojo.from(user);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<UserPojo> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserPojo.from(user));
    }

    @PostMapping("/food/preference//{id:\\d+}")
    public ResponseEntity<List<MealCategoryPojo>> setUserPreferences(@PathVariable("id") Long id, @Valid @RequestBody UserPreferenceDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.setFoodPreferences(id, dto.getCodes()));
    }
}

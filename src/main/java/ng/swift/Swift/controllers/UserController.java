package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.UserPojo;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public UserPojo registerUser(@Valid @RequestBody UserCreationDto dto) {
        User user = userService.registerUser(dto);
        return UserPojo.from(user);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<UserPojo> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserPojo.from(user));
    }
}

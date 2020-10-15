package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.models.User;
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
    public User registerUser(@Valid @RequestBody UserCreationDto dto){
        return userService.registerUser(dto);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }
}

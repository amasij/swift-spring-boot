package ng.swift.Swift.service;

import ng.swift.Swift.dto.LoginDto;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.MealCategoryPojo;
import ng.swift.Swift.pojo.UserPojo;

import java.util.List;
import java.util.Set;

public interface UserService {
    User registerUser(UserCreationDto dto);
    User getUser(Long id);
    User loginUser(LoginDto dto);
    List<MealCategoryPojo> setFoodPreferences(Long userId, List<String> codes);
}
package ng.swift.Swift.service;

import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.models.User;

public interface UserService {
    User registerUser(UserCreationDto dto);
    User getUser(Long id);
}
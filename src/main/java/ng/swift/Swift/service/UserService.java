package ng.swift.Swift.service;

import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.UserPojo;

public interface UserService {
    User registerUser(UserCreationDto dto);
    User getUser(Long id);
}
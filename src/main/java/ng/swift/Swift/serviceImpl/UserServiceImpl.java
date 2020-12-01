package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.EntityStatusConstant;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.UserPojo;
import ng.swift.Swift.repositories.UserRepository;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Date;

@Named
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User registerUser(UserCreationDto dto) {
        User user = new User();
        user.setStatus(EntityStatusConstant.ACTIVE);
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword().trim()));
        user.setDateCreated(new Date());
        return userRepository.save(user);

    }

    @Override
    public User getUser(Long id) {
         return userRepository.findActiveById(id).orElseThrow(() -> new ErrorResponse(HttpStatus.NOT_FOUND, "User not found"));
    }
}

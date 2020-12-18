package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.LoginDto;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.EntityStatusConstant;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.Preference;
import ng.swift.Swift.models.User;
import ng.swift.Swift.pojo.MealCategoryPojo;
import ng.swift.Swift.pojo.UserPojo;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.PreferenceRepository;
import ng.swift.Swift.repositories.UserRepository;
import ng.swift.Swift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MealCategoryRepository mealCategoryRepository;
    private final PreferenceRepository preferenceRepository;

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

    @Override
    public User loginUser(LoginDto dto) {
        String password = bCryptPasswordEncoder.encode(dto.getPassword().trim());
        String identifier = dto.getIdentifier().trim();
        Optional<User> user = userRepository.findActiveByEmailOrPhoneNumberAndPassword(identifier, password);
        return user.orElseThrow(() -> new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid identifier or password"));
    }

    @Transactional
    @Override
    public List<MealCategoryPojo> setFoodPreferences(Long userId, List<String> codes) {
        User user = getUser(userId);
        List<MealCategory> mealCategories = mealCategoryRepository.findActiveByCodes(codes);
        return mealCategories.stream().map(mealCategory -> {
            Preference preference = new Preference();
            preference.setStatus(EntityStatusConstant.ACTIVE);
            preference.setMealCategory(mealCategory);
            preference.setUser(user);
            preferenceRepository.save(preference);
            return MealCategoryPojo.from(mealCategory);
        }).collect(Collectors.toList());
    }
}

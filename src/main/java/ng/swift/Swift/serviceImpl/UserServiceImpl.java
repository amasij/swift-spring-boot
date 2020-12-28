package ng.swift.Swift.serviceImpl;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.dto.LoginDto;
import ng.swift.Swift.dto.UserCreationDto;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.*;
import ng.swift.Swift.pojo.MealCategoryPojo;
import ng.swift.Swift.pojo.UserPojo;
import ng.swift.Swift.repositories.AppRepository;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.PreferenceRepository;
import ng.swift.Swift.repositories.UserRepository;
import ng.swift.Swift.service.UserService;
import ng.swift.Swift.utils.Utils;
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
    private final AppRepository appRepository;

    @Transactional
    @Override
    public User registerUser(UserCreationDto dto) {
        User user = new User();
        user.setStatus(EntityStatusConstant.ACTIVE);
        user.setName(dto.getName());
        user.setPhoneNumber(Utils.formatPhoneNumber(dto.getPhoneNumber().trim()));
        user.setEmail(dto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword().trim()));
        user.setDateCreated(new Date());
        return userRepository.save(user);

    }

    @Override
    public User getUser(Long id) {
        return userRepository.findActiveById(id).orElseThrow(() -> new ErrorResponse(HttpStatus.BAD_REQUEST, "User not found"));
    }

    @Override
    public User loginUser(LoginDto dto) {
        JPAQuery<User> jpaQuery = appRepository.startJPAQuery(QUser.user);
        String identifier = dto.getIdentifier().trim();
        if (Utils.isPhoneNumber(identifier)) {
            jpaQuery.where(QUser.user.phoneNumber.eq(Utils.formatPhoneNumber(identifier)));
        } else {
            jpaQuery.where(QUser.user.email.equalsIgnoreCase(identifier));
        }
        User user = jpaQuery.fetchFirst();
        if(user != null){
            if(bCryptPasswordEncoder.matches(dto.getPassword().trim(),user.getPassword())){
                return user;
            }
        }
        throw new ErrorResponse(HttpStatus.UNAUTHORIZED, Utils.isPhoneNumber(identifier) ? "Invalid phone number or password." : "Invalid email or password.");
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

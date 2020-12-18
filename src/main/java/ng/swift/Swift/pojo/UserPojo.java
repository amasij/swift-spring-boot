package ng.swift.Swift.pojo;

import lombok.Getter;
import lombok.Setter;
import ng.swift.Swift.models.User;

@Getter
@Setter
public class UserPojo {
    private String name;
    private Long id;
    private String phoneNumber;
    private String email;

    public static  UserPojo from(User user){
        UserPojo pojo = new UserPojo();
        pojo.setId(user.getId());
        pojo.setName(user.getName());
        pojo.setPhoneNumber(user.getPhoneNumber());
        pojo.setEmail(user.getEmail());
        return pojo;
    }
}

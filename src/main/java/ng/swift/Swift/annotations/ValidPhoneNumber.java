package ng.swift.Swift.annotations;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.service.PhoneNumberService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Named
public class ValidPhoneNumber implements PhoneNumber.Validator {
    private final PhoneNumberService phoneNumberService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }
        try {
            return phoneNumberService.isValid(value);
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}

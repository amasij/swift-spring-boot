package ng.swift.Swift.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE_USE})
@Constraint(validatedBy = {PhoneNumber.Validator.class})
public @interface PhoneNumber {


        String message() default "INVALID_PHONE_NUMBER";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String value() default "";

        interface Validator extends ConstraintValidator<PhoneNumber, String> {
        }
    }



package com.muhammet.restaurantapplication.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "Geçersiz Telefon Numarası Formatı";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

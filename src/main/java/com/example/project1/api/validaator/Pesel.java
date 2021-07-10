package com.example.project1.api.validaator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PeselValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pesel {
    String message() default "Pesel is to short";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

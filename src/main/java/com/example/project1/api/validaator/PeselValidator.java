package com.example.project1.api.validaator;

import com.example.project1.api.model.NewUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<Pesel, NewUser> {
    @Override
    public boolean isValid(NewUser newUser, ConstraintValidatorContext constraintValidatorContext) {
        String pesel = newUser.getUserPesel();

        return pesel != null && pesel.length() == 11;
    }
}

package com.example.project1.api.model;

import com.example.project1.api.enums.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class NewUser {

    private Long userId;
    @NotEmpty(message = "Name should not be empty")
    private String userName;
    @NotEmpty(message = "Surname should not be empty")
    private String userSurname;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String userEmail;
    @NotEmpty(message = "Phone number should not be empty")
    private String userPhoneNumber;
    @NotEmpty(message = "PESEL number should not be empty")
    @PESEL(message = "PESEL should have proper value")
    private String userPesel;
    private String userLogin;
    private String userPassword;
    private UserRole userRole;
    private boolean isActivated;

}

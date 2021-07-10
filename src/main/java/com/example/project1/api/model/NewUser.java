package com.example.project1.api.model;

import com.example.project1.api.enums.UserRole;
import com.example.project1.api.validaator.Pesel;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Pesel
public class NewUser {

    private Long userId;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPhoneNumber;
    @PESEL(message = "PESEL should have proper value")
    private String userPesel;
    private String userLogin;
    private String userPassword;
    private UserRole userRole;
    private boolean isActivated;

}

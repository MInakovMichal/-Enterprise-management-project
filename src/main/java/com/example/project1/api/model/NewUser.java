package com.example.project1.api.model;

import com.example.project1.api.enums.UserRole;
import com.example.project1.api.validaator.Pesel;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Pesel
public class NewUser {

    private Long userId;
    private String userName;
    private String userSurname;
    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
    private String userEmail;
    private String userPhoneNumber;
//    @PESEL(message = "PESEL should have proper value")
    private String userPesel;
    private String userLogin;
    private String userPassword;
    private UserRole userRole;
    private boolean isActivated;

}

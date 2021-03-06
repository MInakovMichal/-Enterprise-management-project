package com.example.project1.api.model;

import com.example.project1.api.enums.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class User {

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

    @PESEL(message = "PESEL should have proper value")
    private String userPesel;

    @NotEmpty(message = "Login should not be empty")
    private String userLogin;

    @NotEmpty(message = "Password should not be empty")
    private String userPassword;
    private UserRole userRole;
    private boolean isActivated;

    private List<WorkerCalendarDetails> workerCalendarDetails;

}

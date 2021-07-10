package com.example.project1.api.model;

import com.example.project1.api.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long userId;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPhoneNumber;
    private String userPesel;
    private String userLogin;
    private String userPassword;
    private UserRole userRole;
    private boolean isActivated;

    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }
}

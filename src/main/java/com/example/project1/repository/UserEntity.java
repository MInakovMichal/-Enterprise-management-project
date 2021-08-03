package com.example.project1.repository;

import com.example.project1.api.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String userSurname;

    private String userEmail;

    private String userPhoneNumber;

    private String userPesel;

    private String userLogin;

    private String userPassword;

    private boolean isActivated;

    @OneToMany(mappedBy = "user")
    private List<WorkerCalendarDetailsEntity> workerCalendarId = new ArrayList<>();

    @Column(columnDefinition = "ENUM('WORKER', 'ENGINEER', 'CONSTRUCTION_MANAGER', 'DIRECTOR', 'HOLDER')")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private final LocalDateTime userDateOfRegistration = LocalDateTime.now();


}

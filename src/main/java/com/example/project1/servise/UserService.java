package com.example.project1.servise;

import com.example.project1.api.enums.UserRole;
import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.exeptions.UserIsAlreadyExists;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(NewUser newUser) {
        UserEntity entity = UserEntity.builder()
                .userName(newUser.getUserName())
                .userSurname(newUser.getUserSurname())
                .userEmail(newUser.getUserEmail())
                .userPhoneNumber(newUser.getUserPhoneNumber())
                .userPesel(newUser.getUserPesel())
                .userLogin(newUser.getUserLogin())
                .userPassword(passwordEncoder.encode(newUser.getUserPassword()))
//                .userPassword(newUser.getUserPassword())
                .userRole(UserRole.valueOf(UserRole.WORKER.name()))
                .build();
        userRepository.save(entity);
    }

    public List<User> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToPatient)
                .collect(Collectors.toList());
    }

    private User mapToPatient(UserEntity entity) {
        return User.builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .userSurname(entity.getUserSurname())
                .userEmail(entity.getUserEmail())
                .userPhoneNumber(entity.getUserPhoneNumber())
                .userPesel(entity.getUserPesel())
                .userLogin(entity.getUserLogin())
                .userPassword(passwordEncoder.encode(entity.getUserPassword()))
//                .userPassword(entity.getUserPassword())
                .userRole(UserRole.valueOf(UserRole.WORKER.name()))
                .build();
    }


}

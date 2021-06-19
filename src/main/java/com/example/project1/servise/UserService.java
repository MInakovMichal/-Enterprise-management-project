package com.example.project1.servise;

import com.example.project1.api.enums.UserRole;
import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.exeptions.UserIsAlreadyExists;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateUser(User updateUser) {
        userRepository.updateUser(updateUser.getUserId(), updateUser.getUserName(), updateUser.getUserSurname(), updateUser.getUserPesel(), updateUser.getUserEmail(), updateUser.getUserPhoneNumber());
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUser)
                .orElseThrow(() -> new IllegalStateException("User doesn't exist"));
    }

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
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    private User mapToUser(UserEntity entity) {
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

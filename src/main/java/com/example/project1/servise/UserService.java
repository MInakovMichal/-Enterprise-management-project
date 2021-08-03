package com.example.project1.servise;

import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.api.model.WorkerCalendarDetails;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.WorkerCalendarDetailsEntity;
import com.example.project1.repository.WorkerCalendarDetailsRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkerCalendarDetailsService workerCalendarDetailsService;

    @Transactional
    public void updateUser(User updateUser) {
        userRepository.updateUser(updateUser.getUserId(),
                updateUser.getUserName(),
                updateUser.getUserSurname(),
                updateUser.getUserPesel(),
                updateUser.getUserEmail(),
                updateUser.getUserPhoneNumber());
    }

    public User getUserByPesel(String pesel) {
        return userRepository.findByUserPesel(pesel)
                .map(this::mapToUser)
                .orElseThrow(() -> new IllegalStateException("User doesn't exist"));
    }
    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .map(this::mapToUser)
                .orElseThrow(() -> new IllegalStateException("User doesn't exist"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUser)
                .orElseThrow(() -> new IllegalStateException("User doesn't exist"));
    }

    public void registerWorker(NewUser newUser) {
        UserEntity entity = UserEntity.builder()
                .userName(newUser.getUserName())
                .userSurname(newUser.getUserSurname())
                .userEmail(newUser.getUserEmail())
                .userPhoneNumber(newUser.getUserPhoneNumber())
                .userPesel(newUser.getUserPesel())
                .userRole(newUser.getUserRole())
                .isActivated(false)
                .build();
        userRepository.save(entity);
    }


    @Transactional
    public void registerUser(User updateUser) {
        userRepository.addUser(updateUser.getUserPesel(),
                updateUser.getUserName(),
                updateUser.getUserSurname(),
                updateUser.getUserEmail(),
                updateUser.getUserPhoneNumber(),
                updateUser.getUserLogin(),
                passwordEncoder.encode(updateUser.getUserPassword()),
                true
        );

    }

    public boolean checkUser(User addUser) {
        return userRepository.findByUserEmail(addUser.getUserEmail()).isPresent();
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
                .userRole(entity.getUserRole())
                .workerCalendarDetails(workerCalendarDetailsService.mapToWorkerCalendarDetailsModelList(entity.getWorkerCalendarId()))
                .build();
    }

    public boolean isUserActivated(User addUser) {
        boolean activated = false;
        Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(addUser.getUserEmail());
        if (byUserEmail.isPresent()) {
            activated = byUserEmail.get().isActivated();
            return activated;
        }
        return activated;
    }

    public void deleteWorker(Long id) {
        userRepository.deleteById(id);
    }
}

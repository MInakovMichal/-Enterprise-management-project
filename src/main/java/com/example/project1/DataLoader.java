package com.example.project1;

import com.example.project1.api.enums.UserRole;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.WorkPlaceEntity;
import com.example.project1.repository.WorkPlaceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final WorkPlaceRepository workPlaceRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(WorkPlaceRepository workPlaceRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.workPlaceRepository = workPlaceRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity entity = UserEntity.builder()
                .userLogin("Admin")
                .userPassword(passwordEncoder.encode("password"))
                .userRole(UserRole.HOLDER)
                .isActivated(true)
                .build();
        userRepository.save(entity);

        UserEntity entity2 = UserEntity.builder()
                .userName("Jan")
                .userSurname("Nowak")
                .userEmail("write.your.mail")
                .userPhoneNumber("123456")
                .userPesel("71030567164")
                .userRole(UserRole.WORKER)
                .isActivated(false)
                .build();
        userRepository.save(entity2);

        WorkPlaceEntity workPlaceEntity = WorkPlaceEntity.builder()
                .name("Object 1")
                .street("StreetName 1")
                .houseNumber("999")
                .zipCode("20-576")
                .city("City")
                .started(true)
                .ended(false)
                .build();
        workPlaceRepository.save(workPlaceEntity);
    }
}

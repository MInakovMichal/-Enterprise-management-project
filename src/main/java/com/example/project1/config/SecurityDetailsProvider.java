package com.example.project1.config;

import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SecurityDetailsProvider implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByUserLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new User(userEntity.getUserLogin(), userEntity.getUserPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserRole().name())));
//        return User
//                .withUsername(userEntity.getUserLogin())
//                .password(userEntity.getUserPassword())
//                .authorities(userEntity.getUserRole().name())
//                .build();
    }
}

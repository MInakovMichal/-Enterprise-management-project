package com.example.project1.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    Optional<UserEntity> findByUserLogin(String userLogin);

    Optional<UserEntity> findById(Long id);

    @Modifying
    @Query("update UserEntity u set u.userName = :userName, u.userSurname = :userSurname, u.userPesel = :userPesel, u.userEmail = :userEmail, u.userPhoneNumber = :userPhoneNumber where u.userId = :id")
    void updateUser(@Param("id") Long id,
                       @Param("userName") String userName,
                       @Param("userSurname") String userSurname,
                       @Param("userPesel") String userPesel,
                       @Param("userEmail") String userEmail,
                       @Param("userPhoneNumber") String userPhoneNumber);



}

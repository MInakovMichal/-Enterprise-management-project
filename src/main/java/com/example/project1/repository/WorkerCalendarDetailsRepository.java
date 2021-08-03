package com.example.project1.repository;

import com.example.project1.api.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkerCalendarDetailsRepository extends CrudRepository<WorkerCalendarDetailsEntity, Long> {

    List<WorkerCalendarDetailsEntity> findAllByUser(UserEntity user);

    @Query("select w from WorkerCalendarDetailsEntity w where w.addDate = :date and w.user = :user")
    WorkerCalendarDetailsEntity findWithDateAndUser(
            @Param("date") Date date,
            @Param("user") UserEntity user);
}

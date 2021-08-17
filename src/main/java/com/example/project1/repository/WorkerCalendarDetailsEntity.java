package com.example.project1.repository;

import com.example.project1.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "WorkerCalendarDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WorkerCalendarDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String workdayStart;

    private String workdayEnd;

//    private String workingPlace;

    private String tasks;

    private String volume;

    private String attention;

    private Date addDate;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private WorkPlaceEntity workPlace;
}

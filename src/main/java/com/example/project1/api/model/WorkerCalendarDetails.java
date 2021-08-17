package com.example.project1.api.model;


import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerCalendarDetails {

    private long id;
    private String workdayStart;
    private String workdayEnd;
//    private String workingPlace;
    private String tasks;
    private String volume;
    private String attention;
    private long userId;
    private long workPlaceId;
    private String addDate;
}

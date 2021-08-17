package com.example.project1.api.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkPlace {

    private Long workPlaceId;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Street should not be empty")
    private String street;

    @NotEmpty(message = "House number should not be empty")
    private String houseNumber;

    @NotEmpty(message = "Zip code should not be empty")
    private String zipCode;

    @NotEmpty(message = "City should not be empty")
    private String city;

    private boolean started;

    private boolean ended;

    private List<WorkerCalendarDetails> workerCalendarDetails;
}

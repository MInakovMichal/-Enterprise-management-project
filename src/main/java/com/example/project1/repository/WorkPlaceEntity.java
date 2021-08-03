package com.example.project1.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WorkPlace")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WorkPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workPlaceId;

    private String name;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String city;

    private boolean started;

    private boolean ended;

}

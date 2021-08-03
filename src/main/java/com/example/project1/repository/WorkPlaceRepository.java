package com.example.project1.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkPlaceRepository extends CrudRepository<WorkPlaceEntity, Long> {

    List<WorkPlaceEntity> findAll();

    Optional<WorkPlaceEntity> findByWorkPlaceId(Long aLong);

    @Modifying
    @Query("update WorkPlaceEntity u set u.name = :name, u.street = :street, u.houseNumber = :houseNumber, u.zipCode = :zipCode, u.city = :city, u.started = :started, u.ended = :ended where u.workPlaceId = :id")
    void updateWorkPlace(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("street") String street,
                    @Param("houseNumber") String houseNumber,
                    @Param("zipCode") String zipCode,
                    @Param("city") String city,
                    @Param("started") boolean started,
                    @Param("ended") boolean ended);

}

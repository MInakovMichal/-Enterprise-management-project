package com.example.project1.servise;


import com.example.project1.api.model.User;
import com.example.project1.api.model.WorkPlace;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.WorkPlaceEntity;
import com.example.project1.repository.WorkPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkPlaceService {

    @Autowired
    private WorkPlaceRepository workPlaceRepository;
    private final WorkerCalendarDetailsService workerCalendarDetailsService;


    public List<WorkPlace> getAllWorkPlace() {
        return workPlaceRepository.findAll()
                .stream()
                .map(this::mapToWorkPlace)
                .collect(Collectors.toList());
    }

    private WorkPlace mapToWorkPlace(WorkPlaceEntity entity) {
        return WorkPlace.builder()
                .workPlaceId(entity.getWorkPlaceId())
                .name(entity.getName())
                .street(entity.getStreet())
                .houseNumber(entity.getHouseNumber())
                .zipCode(entity.getZipCode())
                .city(entity.getCity())
                .started(entity.isStarted())
                .ended(entity.isEnded())
                .workerCalendarDetails(workerCalendarDetailsService.mapToWorkerCalendarDetailsModelList(entity.getWorkerCalendarId()))                .build();
    }

    public void registerWorkPlace(WorkPlace workPlace) {
        WorkPlaceEntity entity = WorkPlaceEntity.builder()
                .name(workPlace.getName())
                .street(workPlace.getStreet())
                .houseNumber(workPlace.getHouseNumber())
                .zipCode(workPlace.getZipCode())
                .city(workPlace.getCity())
                .started(workPlace.isStarted())
                .ended(workPlace.isEnded())
                .build();
        workPlaceRepository.save(entity);
    }

    public void deleteWorkPlace(Long id) {
        workPlaceRepository.deleteById(id);
    }

    public WorkPlace getWorkPlaceById(Long id) {
        return workPlaceRepository.findByWorkPlaceId(id)
                .map(this::mapToWorkPlace)
                .orElseThrow(() -> new IllegalStateException("Work place doesn't exist"));
    }

    @Transactional
    public void updateWorkPlace(WorkPlace updateWorkPlace) {
        workPlaceRepository.updateWorkPlace(updateWorkPlace.getWorkPlaceId(),
                updateWorkPlace.getName(),
                updateWorkPlace.getStreet(),
                updateWorkPlace.getHouseNumber(),
                updateWorkPlace.getZipCode(),
                updateWorkPlace.getCity(),
                updateWorkPlace.isStarted(),
                updateWorkPlace.isEnded());
    }


}

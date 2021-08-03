package com.example.project1.servise;

import com.example.project1.api.model.User;
import com.example.project1.api.model.WorkerCalendarDetails;
import com.example.project1.repository.UserEntity;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.WorkerCalendarDetailsEntity;
import com.example.project1.repository.WorkerCalendarDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerCalendarDetailsService {

    private final WorkerCalendarDetailsRepository workerCalendarDetailsRepository;
    private final UserRepository userRepository;

    public WorkerCalendarDetails mapToWorkerCalendarDetailsModel(WorkerCalendarDetailsEntity entity) {
        return WorkerCalendarDetails.builder()
                .id(entity.getId())
                .workdayStart(entity.getWorkdayStart())
                .workdayEnd(entity.getWorkdayEnd())
                .workingPlace(entity.getWorkingPlace())
                .tasks(entity.getTasks())
                .volume(entity.getVolume())
                .attention(entity.getAttention())
                .addDate(entity.getAddDate().toString())
                .userId(entity.getUser().getUserId())
                .build();
    }

    public List<WorkerCalendarDetails> mapToWorkerCalendarDetailsModelList(List<WorkerCalendarDetailsEntity> workerCalendarDetailsEntities) {
        ArrayList<WorkerCalendarDetails> workerCalendarDetails = new ArrayList<>();

        for (WorkerCalendarDetailsEntity e : workerCalendarDetailsEntities) {
            workerCalendarDetails.add(mapToWorkerCalendarDetailsModel(e));
        }
        return workerCalendarDetails;
    }

    public List<WorkerCalendarDetails> findAllByUserId(Long userId) {
        Optional<UserEntity> byUserId = userRepository.findByUserId(userId);
        if (byUserId.isPresent()) {
            List<WorkerCalendarDetailsEntity> allByUser = workerCalendarDetailsRepository.findAllByUser(byUserId.get());
            return mapToWorkerCalendarDetailsModelList(allByUser);
        }
        throw new IllegalArgumentException("User is not exist");
    }

    public WorkerCalendarDetails getDateInformation(String date, Long userId) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToFind = formatter.parse(date);
        Optional<UserEntity> byUserId = userRepository.findByUserId(userId);
        if (byUserId.isPresent()) {
            WorkerCalendarDetailsEntity withDateAndUser = workerCalendarDetailsRepository.findWithDateAndUser(dateToFind, byUserId.get());
            if (withDateAndUser != null) {
                return mapToWorkerCalendarDetailsModel(withDateAndUser);
            } else {
                return null;
            }
        }
        throw new IllegalArgumentException("User is not exist");
    }

    public void addCalendar(WorkerCalendarDetails workerCalendarDetails) throws ParseException {
        Optional<UserEntity> byUserId = userRepository.findByUserId(workerCalendarDetails.getUserId());

        WorkerCalendarDetailsEntity entity = WorkerCalendarDetailsEntity.builder()

                .id(workerCalendarDetails.getId())
                .workdayStart(workerCalendarDetails.getWorkdayStart())
                .workdayStart(workerCalendarDetails.getWorkdayStart())
                .workdayEnd(workerCalendarDetails.getWorkdayEnd())
                .workingPlace(workerCalendarDetails.getWorkingPlace())
                .tasks(workerCalendarDetails.getTasks())
                .volume(workerCalendarDetails.getVolume())
                .attention(workerCalendarDetails.getAttention())
                .user(byUserId.get())
                .addDate(new SimpleDateFormat("yyyy-MM-dd").parse(workerCalendarDetails.getAddDate()))
                .build();
        workerCalendarDetailsRepository.save(entity);
    }
}

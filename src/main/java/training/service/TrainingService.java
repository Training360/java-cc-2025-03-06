package training.service;

import lombok.RequiredArgsConstructor;
import training.TrainingDto;
import training.TrainingType;
import training.entity.Training;
import training.repository.TrainingRepository;

import java.util.Set;

@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingDto announce(String name, TrainingType trainingType) {
        Training training = Training.announce(name, trainingType);
        trainingRepository.save(training);
        return new TrainingDto(training.getId(), name, training.getTrainingType());
    }

    public TrainingDto enroll(long trainingId, Set<Long> participantIds) {
        Training training = trainingRepository.findById(trainingId);
        training.enroll(participantIds);
        return new TrainingDto(training.getId(), training.getName(), training.getTrainingType());
    }
}

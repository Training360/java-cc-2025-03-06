package boot;

import lombok.Getter;
import participant.repository.ParticipantRepository;
import participant.service.ParticipantService;
import training.repository.TrainingRepository;
import training.service.TrainingService;

@Getter
public class Context {

    private final ParticipantRepository participantRepository = new ParticipantRepository();

    private final ParticipantService participantService = new ParticipantService(participantRepository);

    private final TrainingRepository trainingRepository = new TrainingRepository();

    private final TrainingService trainingService = new TrainingService(trainingRepository);
}

package participant.service;

import lombok.RequiredArgsConstructor;
import participant.Newsletter;
import participant.ParticipantDto;
import participant.entity.Participant;
import participant.repository.ParticipantRepository;

@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantDto register(String name, Newsletter newsletter) {
        Participant participant = Participant.register(name, newsletter);
        participantRepository.save(participant);
        return new ParticipantDto(participant.getId(), participant.getName(), participant.getNewsletter());
    }

    public ParticipantDto findById(long id) {
        Participant participant = participantRepository.findById(id);
        return new ParticipantDto(participant.getId(), participant.getName(), participant.getNewsletter());
    }

}

package participant.repository;

import participant.entity.Participant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ParticipantRepository {

    private final AtomicLong idGenerator = new AtomicLong();

    private final Map<Long, Participant> participants = new ConcurrentHashMap<>();

    public void save(Participant participant) {
        long id = idGenerator.incrementAndGet();
        participant.setId(id);
        participants.put(id, participant);
    }

    public Participant findById(long id) {
         return participants.get(id);
    }

}

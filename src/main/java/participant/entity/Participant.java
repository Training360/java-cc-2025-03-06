package participant.entity;

import lombok.Getter;
import lombok.Setter;
import participant.Newsletter;

@Getter
public class Participant {

    @Setter
    private Long id;

    private final String name;

    private final Newsletter newsletter;

    Participant(String name, Newsletter newsletter) {
        this.name = name;
        this.newsletter = newsletter;
    }

    public static Participant create(String name, Newsletter newsletter) {
        return new Participant(name, newsletter);
    }
}

package token.repository;

import participant.entity.Participant;
import token.Enrollment;
import token.entity.Token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenRepository {

    private final Map<Enrollment, Token> tokens = new ConcurrentHashMap<>();

    public void save(Token token) {
        tokens.put(token.getEnrollment(), token);
    }

    public Token findByEnrollment(Enrollment enrollment) {
        return tokens.get(enrollment);
    }
}

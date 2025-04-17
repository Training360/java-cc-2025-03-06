package token.repository;

import token.vo.Enrollment;
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

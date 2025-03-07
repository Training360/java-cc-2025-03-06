package token.service;

import token.Enrollment;
import token.TokenLimit;
import token.TokenUsage;
import token.entity.Token;

import java.util.Set;

public class TokenService {

    public void assignTokenLimit(Set<Long> participantIds, long trainingId,
                     TokenLimit tokenLimit) {

    }

    public TokenUsage useTokens(Enrollment enrollment, int amount) {
        Token token = null; // repo

        return token.use(amount);
     }

    public TokenUsage getTokenUsage(Enrollment enrollment) {
        return null;
     }
}

package token.service;

import lombok.RequiredArgsConstructor;
import token.Enrollment;
import token.TokenLimit;
import token.TokenUsage;
import token.entity.Token;
import token.repository.TokenRepository;

import java.util.Set;

@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void assignTokenLimit(Set<Long> participantIds, long trainingId,
                     TokenLimit tokenLimit) {
        for (Long participantId : participantIds) {
            Token token = Token.assign(new Enrollment(trainingId, participantId), tokenLimit);
            tokenRepository.save(token);
        }
    }

    public TokenUsage useTokens(Enrollment enrollment, int amount) {
        Token token = tokenRepository.findByEnrollment(enrollment);
        return token.use(amount);
     }

    public TokenUsage getTokenUsage(Enrollment enrollment) {
        Token token = tokenRepository.findByEnrollment(enrollment);
        // TODO
        return null;
     }
}

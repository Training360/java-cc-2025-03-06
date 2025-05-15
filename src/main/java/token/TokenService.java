package token;

import lombok.RequiredArgsConstructor;
import token.vo.Enrollment;
import token.vo.TokenLimit;
import token.vo.TokenUsage;
import token.entity.Token;
import token.repository.TokenRepository;
import training.TrainingType;

import java.util.Set;

@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void assignTokenLimit(Set<Long> participantIds, long trainingId,
                                 TokenLimit tokenLimit, TrainingType trainingType) {
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

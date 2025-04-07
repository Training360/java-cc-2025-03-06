package token.entity;

import lombok.Getter;
import token.*;

@Getter
public class Token {

    private Enrollment enrollment;

    private TokenLimit tokenLimit;

    private int used;

    Token(Enrollment enrollment, TokenLimit tokenLimit) {
        this.enrollment = enrollment;
        this.tokenLimit = tokenLimit;
    }

    public static Token assign(Enrollment enrollment, TokenLimit tokenLimit) {
        return new Token(enrollment, tokenLimit);
    }

    public TokenUsage use(int amountToUse) {
        return switch (tokenLimit) {
            case UnlimitedTokens _ -> {
                used += amountToUse;
                yield new UnlimitedTokenUsage(used);
            }
            case LimitedTokens(var tokenAmount) -> {
                if (tokenAmount < used + amountToUse) {
                    throw new IllegalStateException("Token limit exceeded for enrollment %s: %d < %d + %d"
                            .formatted(enrollment, tokenAmount, used, amountToUse));
                }
                used += amountToUse;
                int free = tokenAmount - used;
                yield new LimitedTokenUsage(used, tokenAmount, free);
            }
        };
    }
}

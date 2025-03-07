package token.entity;

// TODO: import *
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
        this.used = used;
    }

    public static Token assign(Enrollment enrollment, TokenLimit tokenLimit) {
        return new Token(enrollment, tokenLimit);
    }

    public TokenUsage use(int amount) {
        switch (tokenLimit) {
            // TODO Unused variable
            case UnlimitedTokens token: {
                used += amount;
                return new UnlimitedTokenUsage(used);
            }
            case LimitedTokens token: {
                if (token.amount() < used + amount) {
                    // TODO Exception best practices
                    throw new IllegalStateException("token limit exceeded");
                }
                used += amount;
                return new LimitedTokenUsage(used, token.amount(), token.amount() - used);
            }
            // TODO: forduljon le
            default: {
                throw new IllegalStateException("unknown type");
            }
        }
    }
}

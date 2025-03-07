package token.entity;

// TODO: import *
import token.*;

public class Token {

    private Enrollment enrollment;

    private TokenLimit tokenLimit;

    private int used;

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
                return new LimitedTokenUsage(used, token.amount(), token.amount()- used);
            }
            // TODO: forduljon le
            default: {
                throw new IllegalStateException("unknown type");
            }
        }
    }
}

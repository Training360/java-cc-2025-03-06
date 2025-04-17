package token;

public sealed interface TokenLimit permits LimitedTokens, UnlimitedTokens {

    static TokenLimit unlimited() {
        return new UnlimitedTokens();
    }

    static TokenLimit limited(int amount) {
        return new LimitedTokens(amount);
    }
}

package token;

public record LimitedTokens(int amount) implements TokenLimit {
}

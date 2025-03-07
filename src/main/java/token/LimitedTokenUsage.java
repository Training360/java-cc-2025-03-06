package token;

public record LimitedTokenUsage(int used, int all, int free) implements TokenUsage {
}

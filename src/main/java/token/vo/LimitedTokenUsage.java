package token.vo;

public record LimitedTokenUsage(int used, int all, int free) implements TokenUsage {

    @Override
    public boolean hasAvailable() {
        return free > 0;
    }
}

package token.vo;

public record UnlimitedTokenUsage(int used) implements TokenUsage {

    @Override
    public boolean hasAvailable() {
        return true;
    }
}

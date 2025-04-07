package token;

public sealed interface TokenLimit permits LimitedTokens, UnlimitedTokens {
}

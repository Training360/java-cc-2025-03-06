package token.entity;

import org.junit.jupiter.api.Test;
import token.vo.Enrollment;
import token.vo.LimitedTokenUsage;
import token.vo.TokenLimit;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void useUnlimited() {
        var token = Token.assign(new Enrollment(1L, 1L),
                TokenLimit.unlimited());
        var result = token.use(100_000);
        assertTrue(result.hasAvailable());
    }

    @Test
    void useLimited() {
        var token = Token.assign(new Enrollment(1L, 1L),
                TokenLimit.limited(100));
        var result = token.use(10);
        assertTrue(result.hasAvailable());
        assertInstanceOf(LimitedTokenUsage.class, result);
        assertEquals(90, ((LimitedTokenUsage) result).free());
    }
}
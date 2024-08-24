package io.malek;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "123", "abc123.#%$21312312", "fldgks;hqa232/21,."})
    void shouldThrowExceptionWhenProvidedPasswordIsRawText(String rawPassword) {
        assertThrows(IllegalArgumentException.class, () -> Password.of(rawPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"$2a$12$bhXYI8aoek5MJvoBFXNkAu/h9P4xPJV8Kzphh46BzZaEojdHB5S.e", "$2a$12$gurXOT86B6AzIPgsQeX7zOeaFZcu21LVEaKge/s.24OhAcuo5ipoW"})
    void shouldNotThrowExceptionWhenProvidedPasswordIsHashedByBcrypt(String hashPassword) {
        assertDoesNotThrow(() -> Password.of(hashPassword));
    }

}
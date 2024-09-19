package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.LastName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LastnameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void shouldThrowExceptionWhenLastnameIsBlank(String lastname) {
        assertThrows(IllegalArgumentException.class, () -> LastName.of(lastname));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWhenLastnameIsNull(String lastname) {
        assertThrows(IllegalArgumentException.class, () -> LastName.of(lastname));
    }

    @Test
    void shouldNotThrowExceptionWhenLastnameIsProperlyFilled() {
        final String lastnameValue = "Sparrow";
        assertDoesNotThrow(() -> LastName.of(lastnameValue));
    }

}

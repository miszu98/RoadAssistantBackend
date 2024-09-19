package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.FirstName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FirstnameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void shouldThrowExceptionWhenFirstnameIsBlank(String firstname) {
        assertThrows(IllegalArgumentException.class, () -> FirstName.of(firstname));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWhenFirstnameIsNull(String firstname) {
        assertThrows(IllegalArgumentException.class, () -> FirstName.of(firstname));
    }

    @Test
    void shouldNotThrowExceptionWhenFirstnameIsProperlyFilled() {
        final String firstnameValue = "Jack";
        assertDoesNotThrow(() -> FirstName.of(firstnameValue));
    }

}

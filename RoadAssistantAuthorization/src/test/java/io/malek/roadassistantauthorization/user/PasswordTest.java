package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.Password;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @ParameterizedTest
    @ValueSource(strings = {"ad!dA2", "a2aaAa!", "aaaaa2aaaAaaaaaaaaa!"})
    void shouldThrowExceptionWhenPasswordHasLengthOutOfRange(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.of(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Asdasdad2", "AsdasdssAdad2", "EASASDASAdad2"})
    void shouldThrowExceptionWhenPasswordNotContainsSpecialChar(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.of(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Asdasdad!", "AsdasdssAdad!", "EASASDASAdad!"})
    void shouldThrowExceptionWhenPasswordNotContainsNumber(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.of(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdasdad!2", "asdasdssadad!1", "cdsaddad!44"})
    void shouldThrowExceptionWhenPasswordNotContainsUpperCaseLetter(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.of(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdaSdad!2", "asdasdssDadad!1", "cdaAd!44"})
    void shouldNotThrowExceptionWhenPasswordPassedAllValidators(String password) {
        assertDoesNotThrow(() -> Password.of(password));
    }
}

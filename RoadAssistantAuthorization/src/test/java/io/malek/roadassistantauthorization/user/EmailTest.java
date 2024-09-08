package io.malek.roadassistantauthorization.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"admingmail.pl", "roman@pl", "test@gmail", "appgmail@.pl"})
    void shouldThrowExceptionWhenEmailsHasNotCorrectFormat(String emailValue) {
        assertThrows(IllegalArgumentException.class, () -> Email.of(emailValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {"app@gmail.com", "roman.nowak@wp.pl", "tomasz@onet.pl"})
    void shouldNotThrowExceptionWhenEmailsHasCorrectFormat(String emailValue) {
        assertDoesNotThrow(() -> Email.of(emailValue));
    }

}
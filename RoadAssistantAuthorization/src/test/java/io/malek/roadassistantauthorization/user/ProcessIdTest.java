package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.ProcessId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProcessIdTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void shouldThrowExceptionWhenProcessIdIsBlank(String processId) {
        assertThrows(IllegalArgumentException.class, () -> ProcessId.of(processId));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWhenProcessIdIsNullString(String processId) {
        assertThrows(IllegalArgumentException.class, () -> ProcessId.of(processId));
    }

    @Test
    void shouldNotThrowExceptionWhenProcessIdIsProperlyFilled() {
        final String processIdValue = UUID.randomUUID().toString();
        assertDoesNotThrow(() -> ProcessId.of(processIdValue));
    }
}

package io.malek;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.isNull;

public record Timestamp(LocalDateTime value) {

    public Timestamp {
        if (isNull(value)) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
    }

    public static Timestamp of(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime);
    }

    public static Timestamp now(Clock clock) {
        return new Timestamp(LocalDateTime.now(clock));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timestamp timestamp = (Timestamp) o;

        return Objects.equals(value, timestamp.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

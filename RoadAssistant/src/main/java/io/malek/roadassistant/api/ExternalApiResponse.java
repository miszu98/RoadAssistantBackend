package io.malek.roadassistant.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import static java.util.Objects.isNull;

@Slf4j
record ExternalApiResponse<T>(ApiSourceName apiSourceName, Page<T> roadIncidents) {

    ExternalApiResponse {
        boolean argumentsAreNull = isNull(apiSourceName) || isNull(roadIncidents);
        if (argumentsAreNull) {
            log.warn("Cannot build ExternalApiResponse when arguments [apiSourceName, roadIncidents] are null");
            throw new IllegalArgumentException("Api source name and road incident cannot be null");
        }
    }

    static <T> ExternalApiResponse<T> of(ApiSourceName apiSourceName, Page<T> roadIncidents) {
        return new <T> ExternalApiResponse<T>(apiSourceName, roadIncidents);
    }

}

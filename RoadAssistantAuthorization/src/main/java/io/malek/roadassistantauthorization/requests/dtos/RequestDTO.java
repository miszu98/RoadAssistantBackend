package io.malek.roadassistantauthorization.requests.dtos;

import io.malek.roadassistantauthorization.requests.enums.RequestType;

public record RequestDTO(RequestData requestData, ResponseData responseData,
                         RequestType type, RequestUid requestUid, RequestStatusCode code) {
}

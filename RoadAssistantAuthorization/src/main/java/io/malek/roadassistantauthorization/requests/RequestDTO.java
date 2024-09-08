package io.malek.roadassistantauthorization.requests;

record RequestDTO(RequestData requestData, ResponseData responseData,
                  RequestType type, RequestUid requestUid, RequestStatusCode code) {
}

package io.malek.roadassistantauthorization.requests;

import io.malek.Timestamp;
import io.malek.roadassistantauthorization.requests.dtos.RequestData;
import io.malek.roadassistantauthorization.requests.dtos.RequestStatusCode;
import io.malek.roadassistantauthorization.requests.dtos.RequestUid;
import io.malek.roadassistantauthorization.requests.dtos.ResponseData;
import io.malek.roadassistantauthorization.requests.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
class Request {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RequestType type;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "request_uid"))
    private RequestUid requestUid;

    @Setter
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "request_data"))
    private RequestData requestData;

    @Setter
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "response_data"))
    private ResponseData responseData;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "code"))
    private RequestStatusCode code;

    @Setter
    @Getter
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "created_at"))
    private Timestamp createdAt;
}

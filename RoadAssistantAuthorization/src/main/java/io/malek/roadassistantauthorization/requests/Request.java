package io.malek.roadassistantauthorization.requests;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "request_data"))
    private RequestData requestData;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "response_data"))
    private ResponseData responseData;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "code"))
    private RequestStatusCode code;

}

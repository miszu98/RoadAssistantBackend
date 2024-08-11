package io.malek.roadassistantdatagenerator.incidents;

import io.malek.IncidentTime;
import io.malek.Latitude;
import io.malek.Longitude;
import io.malek.RoadIncidentUuid;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@Table(name = "road_incidents")
class RoadIncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "uuid"))
    private RoadIncidentUuid uuid = RoadIncidentUuid.newOne();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "longitude"))
    private Longitude longitude;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "latitude"))
    private Latitude latitude;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "incident_time"))
    private IncidentTime incidentTime;

    public RoadIncidentEntity() {
        this.uuid = RoadIncidentUuid.newOne();
    }
}

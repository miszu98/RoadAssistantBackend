package io.malek.roadassistantdatagenerator.incidents;

import io.malek.IncidentTime;
import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/road-incidents")
class RoadIncidentController {
    private final RoadIncidentFacade roadIncidentFacade;

    @GetMapping
    ResponseEntity<List<RoadIncident>> findRoadIncidentsByTime(@RequestParam LocalDate incidentTime, Pageable pageable) {
        return ResponseEntity.ok().body(roadIncidentFacade.findRoadIncidentsByDate(IncidentTime.of(incidentTime.atStartOfDay()), pageable));
    }

}

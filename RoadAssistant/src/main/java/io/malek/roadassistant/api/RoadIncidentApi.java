package io.malek.roadassistant.api;


import java.time.LocalDate;

interface RoadIncidentApi<T> {

    T refreshRoadIncidents(LocalDate incidentTime);

}

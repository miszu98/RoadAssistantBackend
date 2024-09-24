package io.malek.roadassistantauthorization.requests;

import io.malek.roadassistantauthorization.requests.dtos.RequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface RequestMapper {

    Request mapToRequest(RequestDTO requestDTO);
    
}

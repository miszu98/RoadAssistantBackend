package io.malek.roadassistantauthorization.requests;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface RequestMapper {

    Request mapToRequest(RequestDTO requestDTO);
    
}

package com.prueba_tecnica.mapper;

import com.prueba_tecnica.dto.FootballTeamRequestDTO;
import com.prueba_tecnica.model.FootballTeam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.mapstruct.*;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface FootballTeamMapper {
    FootballTeamMapper INSTANCE = Mappers.getMapper(FootballTeamMapper.class);

    FootballTeam toEntity(FootballTeamRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FootballTeam toEntityEdit(FootballTeamRequestDTO dto, @MappingTarget FootballTeam footballTeam);
}
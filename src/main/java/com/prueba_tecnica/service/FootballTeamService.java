package com.prueba_tecnica.service;

import com.prueba_tecnica.dto.FootballTeamRequestDTO;
import com.prueba_tecnica.model.FootballTeam;

import java.util.List;

public interface FootballTeamService {
    List<FootballTeam> findAll();

    FootballTeam findFootballTeamById(Long id);

    List<FootballTeam> findFootballTeamByName(String nombre);

    FootballTeam createFootballTeam(FootballTeamRequestDTO equipoDTO);

    FootballTeam updateFootballTeam(FootballTeamRequestDTO equipoDTO, Long id);

    void deleteFootballTeam(Long id);
}

package com.prueba_tecnica.service;

import com.prueba_tecnica.dto.FootballTeamRequestDTO;
import com.prueba_tecnica.exceptions.ExceptionFactory;
import com.prueba_tecnica.exceptions.custom.InvalidInputException;
import com.prueba_tecnica.exceptions.custom.ResourseNotFoundException;
import com.prueba_tecnica.mapper.FootballTeamMapper;
import com.prueba_tecnica.model.FootballTeam;
import com.prueba_tecnica.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FootballTeamServiceImpl implements FootballTeamService {
    FootballTeamRepository footballTeamRepository;

    @Autowired
    public FootballTeamServiceImpl(FootballTeamRepository footballTeamRepository) {
        this.footballTeamRepository = footballTeamRepository;
    }

    @Override
    public List<FootballTeam> findAll() {
        return footballTeamRepository.findAll();
    }

    @Override
    public FootballTeam findFootballTeamById(Long id) {
        return findById(id);
    }

    public FootballTeam findById(Long id){
        return footballTeamRepository.findById(id).orElseThrow(() -> ExceptionFactory.createException(ResourseNotFoundException.class, "El equipo con ID " + id + " no fue encontrado."));
    }

    @Override
    public List<FootballTeam> findFootballTeamByName(String name) {
        if (!existsByName(name)){
            throw ExceptionFactory.createException(ResourseNotFoundException.class, "El equipo con nombre " + name + " no fue encontrado.");
        }
        return footballTeamRepository.findByNombreContainingIgnoreCase(name);
    }

    private boolean existsByName(String name){
        return footballTeamRepository.existsByNombreContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public FootballTeam createFootballTeam(FootballTeamRequestDTO footballTeamDTO){
        if (existsByName(footballTeamDTO.getNombre())) {
            throw ExceptionFactory.createException(InvalidInputException.class, "Ya existe un equipo con ese nombre.");
        }
        FootballTeam footballTeam = FootballTeamMapper.INSTANCE.toEntity(footballTeamDTO);
        footballTeamRepository.save(footballTeam);
        return footballTeam;
    }

    @Override
    @Transactional
    public FootballTeam updateFootballTeam(FootballTeamRequestDTO footballTeamDTO, Long id){
        if(!existsTeam(id)){
            throw ExceptionFactory.createException(ResourseNotFoundException.class, "Equipo no encontrado");
        }
        if (existsByName(footballTeamDTO.getNombre()) && !footballTeamDTO.getNombre().equals(findById(id).getNombre())) {
            throw ExceptionFactory.createException(InvalidInputException.class, "Ya existe un equipo con ese nombre.");
        }
        FootballTeam teamFind = findById(id);
        FootballTeam footballTeam = FootballTeamMapper.INSTANCE.toEntityEdit(footballTeamDTO, teamFind);
        footballTeamRepository.save(footballTeam);
        return footballTeam;
    }

    private boolean existsTeam(Long id){
        return footballTeamRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteFootballTeam(Long id){
        if(!existsTeam(id)){
            throw ExceptionFactory.createException(ResourseNotFoundException.class, "Equipo no encontrado");
        }
        footballTeamRepository.deleteById(id);
    }
}

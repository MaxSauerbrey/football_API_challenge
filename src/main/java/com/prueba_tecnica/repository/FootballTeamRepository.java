package com.prueba_tecnica.repository;


import com.prueba_tecnica.model.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {
    List<FootballTeam> findByNombreContainingIgnoreCase(String name);
    boolean existsByNombreContainingIgnoreCase(String name);
}
package com.prueba_tecnica.controller;

import com.prueba_tecnica.dto.FootballTeamRequestDTO;
import com.prueba_tecnica.model.FootballTeam;
import com.prueba_tecnica.service.FootballTeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class FootballTeamController {
    FootballTeamService footballTeamService;

    @Autowired
    public FootballTeamController(FootballTeamService footballTeamService) {
        this.footballTeamService = footballTeamService;
    }

    @Operation(summary = "Obtiene todos los equipos de fútbol", description = "Devuelve una lista de todos los equipos de fútbol registrados.")
    @GetMapping
    public List<FootballTeam> findAll() {
        return footballTeamService.findAll();
    }

    @Operation(
            summary = "Obtiene un equipo de fútbol por ID",
            description = "Devuelve los detalles de un equipo específico dado su ID.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Equipo encontrado",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FootballTeam.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FootballTeam> findById(@Parameter(example = "1",description = "ID del equipo")@PathVariable Long id) {
        FootballTeam footballTeam = footballTeamService.findFootballTeamById(id);
        return new ResponseEntity<>(footballTeam, HttpStatus.OK);
    }

    @Operation(
            summary = "Busca equipos por nombre",
            description = "Permite buscar equipos de fútbol mediante su nombre."
    )
    @GetMapping("/buscar")
    public ResponseEntity<List<FootballTeam>> findByName(@Parameter(example = "Real Madrid")@RequestParam("nombre") String nombre) {
        List<FootballTeam> footballTeamList = footballTeamService.findFootballTeamByName(nombre);
        return new ResponseEntity<>(footballTeamList, HttpStatus.OK);
    }

    @Operation(
            summary = "Crea un nuevo equipo de fútbol",
            description = "Crea un nuevo equipo de fútbol con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del equipo de fútbol a crear",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FootballTeamRequestDTO.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Equipo creado",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FootballTeam.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<FootballTeam> create(@Valid @RequestBody FootballTeamRequestDTO equipo){
        FootballTeam footballTeamCreated = footballTeamService.createFootballTeam(equipo);
        return new ResponseEntity<>(footballTeamCreated, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualiza un equipo de fútbol",
            description = "Actualiza los detalles de un equipo de fútbol dado su ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del equipo de fútbol a actualizar",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FootballTeamRequestDTO.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Equipo actualizado",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FootballTeam.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Equipo no encontrado"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<FootballTeam> update(@Valid @RequestBody FootballTeamRequestDTO equipo,@Parameter(example = "1") @PathVariable Long id){
        FootballTeam footballTeamEdited = footballTeamService.updateFootballTeam(equipo, id);
        return new ResponseEntity<>(footballTeamEdited, HttpStatus.OK);
    }

    @Operation(
            summary = "Elimina un equipo de fútbol",
            description = "Elimina un equipo de fútbol dado su ID.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Equipo eliminado"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Equipo no encontrado"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<FootballTeam> delete(@Parameter(example = "1")@PathVariable Long id){
        footballTeamService.deleteFootballTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

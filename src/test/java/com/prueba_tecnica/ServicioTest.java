package com.prueba_tecnica;

import com.prueba_tecnica.exceptions.custom.ResourseNotFoundException;
import com.prueba_tecnica.model.FootballTeam;
import com.prueba_tecnica.repository.FootballTeamRepository;
import com.prueba_tecnica.service.FootballTeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FootballTeamServiceImplTest {

    @Mock
    private FootballTeamRepository footballTeamRepository;

    @InjectMocks
    private FootballTeamServiceImpl footballTeamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {

        List<FootballTeam> expectedTeams = Arrays.asList(
                new FootballTeam(1L, "Team A"),
                new FootballTeam(2L, "Team B")
        );
        when(footballTeamRepository.findAll()).thenReturn(expectedTeams);
        List<FootballTeam> actualTeams = footballTeamService.findAll();
        assertEquals(expectedTeams, actualTeams);
        verify(footballTeamRepository).findAll();
    }


    @Test
    void findById_OK() {
        Long id = 1L;
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setId(id);
        footballTeam.setNombre("FootballTeam Test");

        when(footballTeamRepository.findById(id)).thenReturn(Optional.of(footballTeam));

        FootballTeam result = footballTeamService.findFootballTeamById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("FootballTeam Test", result.getNombre());

        assertEquals(footballTeam, result);
        verify(footballTeamRepository).findById(id);
    }

    @Test
    void findById_Error() {
        Long id = 1L;

        when(footballTeamRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourseNotFoundException.class, () -> footballTeamService.findById(id));

        verify(footballTeamRepository, times(1)).findById(id);
    }

    @Test
    void findFootballTeamById_OK() {
        Long id = 1L;
        FootballTeam expectedTeam = new FootballTeam(id, "Team A");
        when(footballTeamRepository.findById(id)).thenReturn(Optional.of(expectedTeam));

        FootballTeam actualTeam = footballTeamService.findFootballTeamById(id);

        assertEquals(expectedTeam, actualTeam);
        verify(footballTeamRepository).findById(id);
    }

    @Test
    void findFootballTeamById_Error() {
        Long id = 1L;
        when(footballTeamRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourseNotFoundException.class, () -> footballTeamService.findFootballTeamById(id));
        verify(footballTeamRepository).findById(id);
    }

}






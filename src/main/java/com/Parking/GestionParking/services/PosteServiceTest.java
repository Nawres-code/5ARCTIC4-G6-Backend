package com.Parking.GestionParking.services;


import com.Parking.GestionParking.entities.Poste;
import com.Parking.GestionParking.repository.IPosteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class PosteServiceTest {
    @Mock
    private IPosteRepository posteRepo;

    @InjectMocks
    private GestionPosteImpl gestionPoste;

    private Poste poste;

    @BeforeEach
    void setup() {
        poste = new Poste();
        poste.setIdPoste(1);
        poste.setTitle("Test Title");
    }

    @Test
    void addPoste_ShouldSavePoste_WhenTitleDoesNotContainSensitiveWords() {
        when(posteRepo.save(poste)).thenReturn(poste);

        Poste result = gestionPoste.addPoste(poste);

        assertEquals(poste, result);
        verify(posteRepo, times(1)).save(poste);
    }

    @Test
    void addPoste_ShouldThrowException_WhenTitleContainsSensitiveWords() {
        poste.setTitle("This title contains yosser");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionPoste.addPoste(poste);
        });

        assertEquals("Le titre contient des mots sensibles.", exception.getMessage());
        verify(posteRepo, never()).save(poste);
    }

    @Test
    void retrieveAllPoste_ShouldReturnListOfPoste() {
        List<Poste> postes = List.of(poste);
        when(posteRepo.findAll()).thenReturn(postes);

        List<Poste> result = gestionPoste.retrieveAllPoste();

        assertEquals(postes, result);
        verify(posteRepo, times(1)).findAll();
    }

    @Test
    void updatePoste_ShouldUpdatePoste_WhenTitleDoesNotContainSensitiveWords() {
        when(posteRepo.save(poste)).thenReturn(poste);

        Poste result = gestionPoste.updatePoste(poste);

        assertEquals(poste, result);
        verify(posteRepo, times(1)).save(poste);
    }

    @Test
    void updatePoste_ShouldThrowException_WhenTitleContainsSensitiveWords() {
        poste.setTitle("Sensitive title chbinou");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionPoste.updatePoste(poste);
        });

        assertEquals("Le titre contient des mots sensibles.", exception.getMessage());
        verify(posteRepo, never()).save(poste);
    }

    @Test
    void retrievePoste_ShouldReturnPoste_WhenPosteExists() {
        when(posteRepo.findById(1)).thenReturn(Optional.of(poste));

        Poste result = gestionPoste.retrievePoste(1);

        assertEquals(poste, result);
        verify(posteRepo, times(1)).findById(1);
    }

    @Test
    void retrievePoste_ShouldReturnNull_WhenPosteDoesNotExist() {
        when(posteRepo.findById(1)).thenReturn(Optional.empty());

        Poste result = gestionPoste.retrievePoste(1);

        assertNull(result);
        verify(posteRepo, times(1)).findById(1);
    }

    @Test
    void removePoste_ShouldDeletePoste_WhenPosteExists() {
        gestionPoste.removePoste(1);

        verify(posteRepo, times(1)).deleteById(1);
    }

    @Test
    void searchByTitle_ShouldReturnListOfPoste_WhenTitleMatches() {
        List<Poste> postes = List.of(poste);
        when(posteRepo.findByTitleContainingIgnoreCase("Test")).thenReturn(postes);

        List<Poste> result = gestionPoste.searchByTitle("Test");

        assertEquals(postes, result);
        verify(posteRepo, times(1)).findByTitleContainingIgnoreCase("Test");
    }
}

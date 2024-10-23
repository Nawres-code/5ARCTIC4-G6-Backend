package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Poste;
import com.Parking.GestionParking.repository.IPosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GestionPosteImpl implements IGestionPoste{
    @Autowired
    IPosteRepository posteRepo ;

    private final List<String> motsSensibles = Arrays.asList("chbinou", "yosser");

    @Override
    public Poste addPoste(Poste poste) {
        if (contientMotsSensibles(poste.getTitle())) {
            throw new IllegalArgumentException("Le titre contient des mots sensibles.");
        }
        return posteRepo.save(poste);
    }

    @Override
    public List<Poste> retrieveAllPoste() {
        return (List<Poste>) posteRepo.findAll();
    }

    @Override
    public Poste updatePoste(Poste poste) {
        if (contientMotsSensibles(poste.getTitle())) {
            throw new IllegalArgumentException("Le titre contient des mots sensibles.");
        }
        return posteRepo.save(poste);
    }

    @Override
    public Poste retrievePoste(Integer idPoste) {
        return posteRepo.findById(idPoste).orElse(null);
    }

    @Override
    public void removePoste(Integer idPoste) {
        posteRepo.deleteById(idPoste);
    }

    @Override
    public List<Poste> searchByTitle(String title) {
        return posteRepo.findByTitleContainingIgnoreCase(title);
    }

    // Méthode pour vérifier si un titre contient des mots sensibles
    private boolean contientMotsSensibles(String titre) {
        for (String mot : motsSensibles) {
            if (titre.toLowerCase().contains(mot.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}

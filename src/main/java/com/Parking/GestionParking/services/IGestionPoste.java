package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Poste;

import java.util.List;

public interface IGestionPoste {
    Poste addPoste(Poste poste);
    List<Poste> retrieveAllPoste();
    Poste updatePoste(Poste poste);
    Poste retrievePoste (Integer idPoste);
    void removePoste (Integer idPoste);
    List<Poste> searchByTitle(String title);

}

package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Reclamation;

import java.util.List;

public interface IGestionReclamation {
    Reclamation addReclamation(Reclamation reclamation);
    List<Reclamation> retrieveAllReclamation();
    Reclamation updateReclamation(Reclamation reclamation);
    Reclamation retrieveReclamation (Integer idReclamation);
    void deleteReclamation (Integer idreclamation);

    List<Reclamation> getReclamationByEmail(String email);
    Reclamation acceptReclamationByEmail(String email);
    Reclamation RejectReclamationByEmail(String email);

}

package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Reclamation;
import com.Parking.GestionParking.entities.ReclamationType;
import com.Parking.GestionParking.repository.IReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionReclamationImpl implements IGestionReclamation{
    @Autowired
    IReclamationRepository iReclamationRepository;

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        return iReclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> retrieveAllReclamation() {
        return (List<Reclamation>) iReclamationRepository.findAll();
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        return iReclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation retrieveReclamation(Integer idReclamation) {
        return iReclamationRepository.findById(idReclamation).orElse(null);
    }

    @Override
    public void deleteReclamation(Integer idreclamation) {
        iReclamationRepository.deleteById(idreclamation);
    }




    @Override
    public List<Reclamation> getReclamationByEmail(String email) {
        return iReclamationRepository.findByEmail(email);
    }

    @Override
    public Reclamation acceptReclamationByEmail(String email) {
        // Find reclamation(s) by email
        List<Reclamation> reclamations = iReclamationRepository.findByEmail(email);

        // Check if reclamation(s) exist and are pending
        if (reclamations != null && !reclamations.isEmpty()) {
            for (Reclamation reclamation : reclamations) {

                if (reclamation.getStatus().equals(ReclamationType.Pending) ) {
                    // Set status to Accepted
                    reclamation.setStatus(ReclamationType.Treated);
                    // Save the updated reclamation
                    return iReclamationRepository.save(reclamation);
                }
            }
        }

        return null; // No reclamation found or all reclamation(s) are not pending
    }

    @Override
    public Reclamation RejectReclamationByEmail(String email) {
        // Find reclamation(s) by email
        List<Reclamation> reclamations = iReclamationRepository.findByEmail(email);

        // Check if reclamation(s) exist and are pending
        if (reclamations != null && !reclamations.isEmpty()) {
            for (Reclamation reclamation : reclamations) {

                if (reclamation.getStatus().equals(ReclamationType.Pending) ) {
                    // Set status to Rejected
                    reclamation.setStatus(ReclamationType.Rejected);
                    // Save the updated reclamation
                    return iReclamationRepository.save(reclamation);
                }
            }
        }

        return null; // No reclamation found or all reclamation(s) are not pending
    }



}

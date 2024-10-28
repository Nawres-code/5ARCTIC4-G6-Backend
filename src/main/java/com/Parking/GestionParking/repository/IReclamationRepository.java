package com.Parking.GestionParking.repository;

import com.Parking.GestionParking.entities.Reclamation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReclamationRepository extends CrudRepository<Reclamation,Integer> {
    List<Reclamation> findByEmail(String email);
    @Query(value = "SELECT COUNT(*) FROM Reclamation", nativeQuery = true)
    long countReclamation();

    @Query("SELECT COUNT(r) FROM Reclamation r WHERE r.status = 'Pending'")
    long countPendingReclamations();


    @Query("SELECT COUNT(r) FROM Reclamation r WHERE r.status = 'Rejected'")
    long countProcessingReclamations();


    @Query("SELECT COUNT(r) FROM Reclamation r WHERE r.status = 'Treated'")
    long countAcceptedReclamations();

}

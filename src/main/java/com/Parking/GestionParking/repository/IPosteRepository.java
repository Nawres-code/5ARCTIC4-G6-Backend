package com.Parking.GestionParking.repository;

import com.Parking.GestionParking.entities.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPosteRepository extends JpaRepository<Poste, Integer> {
    List<Poste> findByTitleContainingIgnoreCase(String title);

    @Query(value = "SELECT COUNT(*) FROM Poste", nativeQuery = true)
    long countPostes();
}

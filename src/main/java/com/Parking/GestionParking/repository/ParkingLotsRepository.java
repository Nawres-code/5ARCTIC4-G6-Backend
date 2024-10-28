package com.Parking.GestionParking.repository;


import com.Parking.GestionParking.entities.ParkingLots;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ParkingLotsRepository extends CrudRepository<ParkingLots, Long> {
    @Query("SELECT COUNT(p) FROM ParkingLots p")
    long countAllParkingLots();

    @Query("SELECT pl FROM ParkingLots pl ORDER BY SIZE(pl.parkingSpots) DESC")
    List<ParkingLots> findMostVisitedLots(Pageable pageable);

}
package com.Parking.GestionParking.repository;


import com.Parking.GestionParking.entities.ParkingSpot;
import com.Parking.GestionParking.entities.SpotType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;




import java.util.List;

@Repository
public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Long> {
    @Query("SELECT COUNT(s) FROM ParkingSpot s")
    long countAllParkingSpots();

    @Query("SELECT COUNT(ps) FROM ParkingSpot ps WHERE ps.spotType = :spotType")
    Long findMostVisitedSpotTypeCount(@Param("spotType") SpotType spotType);





   /* @Query("SELECT ps FROM ParkingSpot ps WHERE ps.idParkingSpot IN (SELECT p.idParkingSpot FROM ParkingLots pl " +
            "JOIN pl.parkingSpots p " +
            "GROUP BY p.idParkingSpot " +
            "ORDER BY SIZE(p.reservations) DESC)")
    ParkingSpot findMostVisitedSpotInEachLot();*/

}
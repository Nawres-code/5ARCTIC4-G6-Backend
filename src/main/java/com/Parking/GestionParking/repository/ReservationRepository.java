package com.Parking.GestionParking.repository;


import com.Parking.GestionParking.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) FROM Reservation r")
    long countAllReservations();

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.date >= :startDate")
    Long countReservationsSinceLastWeek(Date startDate);

}
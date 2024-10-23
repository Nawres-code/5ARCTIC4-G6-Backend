package com.Parking.GestionParking.services;

import com.Parking.GestionParking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service

public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    public Long getReservationCountSinceLastWeek() {
        Date startDateOfLastWeek = Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reservationRepository.countReservationsSinceLastWeek(startDateOfLastWeek);
    }

}

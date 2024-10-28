package com.Parking.GestionParking.controller;
import com.Parking.GestionParking.entities.ParkingLots;
import com.Parking.GestionParking.entities.ParkingSpot;
import com.Parking.GestionParking.entities.Reservation;

import com.Parking.GestionParking.entities.SpotType;
import com.Parking.GestionParking.repository.ParkingLotsRepository;
import com.Parking.GestionParking.repository.ParkingSpotRepository;
import com.Parking.GestionParking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservationdashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationDashboardController {

    private final ParkingLotsRepository parkingLotsRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationDashboardController(ParkingLotsRepository parkingLotsRepository,
                                          ParkingSpotRepository parkingSpotRepository,
                                          ReservationRepository reservationRepository) {
        this.parkingLotsRepository = parkingLotsRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/lots-count")
    public ResponseEntity<Long> getParkingLotsCount() {
        long totalLots = parkingLotsRepository.countAllParkingLots();
        return ResponseEntity.ok(totalLots);
    }

    @GetMapping("/spots-count")
    public ResponseEntity<Long> getParkingSpotsCount() {
        long totalSpots = parkingSpotRepository.countAllParkingSpots();
        return ResponseEntity.ok(totalSpots);
    }

    @GetMapping("/reservations-count")
    public ResponseEntity<Long> getReservationsCount() {
        long totalReservation = reservationRepository.countAllReservations();
        return ResponseEntity.ok(totalReservation);
    }

    @GetMapping("/most-visited-spot")
    public ResponseEntity<Map<SpotType, Long>> getMostVisitedSpotTypeInEachType() {
        Map<SpotType, Long> mostVisitedSpotTypesByType = new HashMap<>();
        for (SpotType spotType : SpotType.values()) {
            Long mostVisitedSpotTypeCount = parkingSpotRepository.findMostVisitedSpotTypeCount(spotType);
            mostVisitedSpotTypesByType.put(spotType, mostVisitedSpotTypeCount);
        }
        return ResponseEntity.ok(mostVisitedSpotTypesByType);
    }





    @GetMapping("/most-visited-lot")
    public ResponseEntity<ParkingLots> getMostVisitedLot() {
        Pageable pageable = PageRequest.of(0, 1);
        List<ParkingLots> mostVisitedLots = parkingLotsRepository.findMostVisitedLots(pageable);
        if (!mostVisitedLots.isEmpty()) {
            ParkingLots mostVisitedLot = mostVisitedLots.get(0);
            return ResponseEntity.ok(mostVisitedLot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /*@GetMapping("/most-visited-spot-in-each-lot")
    public ResponseEntity<ParkingSpot> getMostVisitedSpotInEachLot() {
        ParkingSpot mostVisitedSpot = parkingSpotRepository.findMostVisitedSpotInEachLot();
        return ResponseEntity.ok(mostVisitedSpot);
    }*/





}














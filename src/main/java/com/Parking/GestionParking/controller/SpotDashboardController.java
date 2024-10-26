package com.Parking.GestionParking.controller;


import com.Parking.GestionParking.entities.SpotType;
import com.Parking.GestionParking.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotDashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class SpotDashboardController {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @GetMapping("/car")
    public Long getCarCount() {
        return parkingSpotRepository.findMostVisitedSpotTypeCount(SpotType.CAR);
    }

    @GetMapping("/handicapped")
    public Long getHandicappedCount() {
        return parkingSpotRepository.findMostVisitedSpotTypeCount(SpotType.HANDICAPPED);
    }

    @GetMapping("/motorcycles")
    public Long getMotorcyclesCount() {
        return parkingSpotRepository.findMostVisitedSpotTypeCount(SpotType.MOTORCYCLES);
    }

    @GetMapping("/bicycle")
    public Long getBicycleCount() {
        return parkingSpotRepository.findMostVisitedSpotTypeCount(SpotType.BICYCLE);
    }
    @GetMapping("/scooter")
    public Long getScooterCount() {
        return parkingSpotRepository.findMostVisitedSpotTypeCount(SpotType.SCOOTER);
    }

}

package com.Parking.GestionParking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ParkingSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParkingSpot;
    private int numSpot;
    @Enumerated(EnumType.STRING)
    private SpotType spotType;
    // Other fields and methods

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    @JsonIgnoreProperties("parkingSpots")
    private ParkingLots parkingLot;

    @OneToMany(mappedBy = "parkingSpot")
    @JsonIgnoreProperties("parkingSpot")
    private List<Reservation> reservations;
}
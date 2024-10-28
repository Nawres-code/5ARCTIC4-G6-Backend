package com.Parking.GestionParking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private int numRes;
    private Date date;
    private Date duration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("reservations")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parking_spot_id")
    @JsonIgnoreProperties("reservations")
    private ParkingSpot parkingSpot;


    @OneToOne(mappedBy = "reservation")
    @JsonIgnoreProperties("reservation")
    private Ticket ticket;


}
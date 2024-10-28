package com.Parking.GestionParking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReclamation;
    private String title;
    @Enumerated(EnumType.STRING)
    private ReclamationType status = ReclamationType.Pending;
    // Default value set to Pending
    private String content;
    private String userName;
    private  String email ;
    private LocalDateTime date = LocalDateTime.now(); // Variable date with default value of current date and time


}

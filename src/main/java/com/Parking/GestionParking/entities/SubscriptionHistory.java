package com.Parking.GestionParking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor

public class SubscriptionHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubHis;
    private Long numSubHistory;
    @ManyToOne
    @JoinColumn(name = "idSub")
    private Subscription subscription;
    private String action;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}

package com.Parking.GestionParking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idSub;
    private Date DateDebut;
    private Date DateExpiration;
    private Float Price;
    @Enumerated(EnumType.STRING)
    SubscriptionType SubType;
    @JsonIgnore
    @OneToMany(mappedBy = "subscription")
    private List<SubscriptionHistory> subscriptionHistories;
    @ManyToOne
    private User user;

}

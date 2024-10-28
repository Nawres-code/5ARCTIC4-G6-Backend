package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.SubscriptionHistory;

import java.util.List;


public interface IGestionSubscriptionHistory {
    SubscriptionHistory assignHistoryToSubscription(Long numSubHistory , Long numSubscription);
    List<SubscriptionHistory> retrieveAllSubscriptionHistory();
}

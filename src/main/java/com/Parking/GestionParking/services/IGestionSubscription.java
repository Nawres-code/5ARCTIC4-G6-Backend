package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Subscription;


import java.util.List;

public interface IGestionSubscription {
    List<Subscription> retrieveAllSubscription();
    Subscription addSubscription(Subscription subscription);
    Subscription updateSubscription (Subscription subscription);
    Subscription retrieveSubscription (Long id);
    void deleteSubscription(Long subscriptionId);
}

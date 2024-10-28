package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Subscription;
import com.Parking.GestionParking.entities.SubscriptionHistory;
import com.Parking.GestionParking.repository.ISubscriptionHistoryRepository;
import com.Parking.GestionParking.repository.ISubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class GestionSubscriptionImpl implements IGestionSubscription {
    @Autowired
    ISubscriptionRepository SubscriptionRepo;
    @Autowired
    ISubscriptionHistoryRepository subscriptionHistoryRepository;

    public List<Subscription> retrieveAllSubscription() {
        // TODO Auto-generated method stub
        log.info("*****************"+ SubscriptionRepo.findAll().size());
        return SubscriptionRepo.findAll();
    }

    ;

    @Override
    public Subscription addSubscription(Subscription subscription) {
        Subscription savedSubscription = SubscriptionRepo.save(subscription);

        // Create and save SubscriptionHistory entry for creation
        saveSubscriptionHistory(savedSubscription, "CREATED");

        return savedSubscription;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        Subscription updatedSubscription = SubscriptionRepo.save(subscription);

        // Create and save SubscriptionHistory entry for update
        saveSubscriptionHistory(updatedSubscription, "UPDATED");

        return updatedSubscription;
    }

    @Override
    public Subscription retrieveSubscription(Long idSub) {
        // TODO Auto-generated method stub
        return SubscriptionRepo.findById(idSub).get();
    }

    public void deleteSubscription(Long idSub) {
        Subscription deletedSubscription = SubscriptionRepo.findById(idSub)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + idSub));

        // Update references in SubscriptionHistory entities
        for (SubscriptionHistory history : deletedSubscription.getSubscriptionHistories()) {
            history.setSubscription(null);
        }

        // Now delete the subscription
        SubscriptionRepo.delete(deletedSubscription);
    }





    private void saveSubscriptionHistory(Subscription subscription, String action) {
        SubscriptionHistory historyEntry = new SubscriptionHistory();
        historyEntry.setSubscription(subscription);
        historyEntry.setAction(action);
        historyEntry.setCreatedAt(LocalDateTime.now());
        subscriptionHistoryRepository.save(historyEntry);
    }


}

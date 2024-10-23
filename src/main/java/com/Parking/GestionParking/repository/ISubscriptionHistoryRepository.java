package com.Parking.GestionParking.repository;

import com.Parking.GestionParking.entities.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, Long> {
}

package com.Parking.GestionParking;
import com.Parking.GestionParking.entities.SubscriptionType;
import com.Parking.GestionParking.services.GestionSubscriptionImpl;
import com.Parking.GestionParking.services.GestionSubscriptionHistoryImpl;

import com.Parking.GestionParking.entities.Subscription;
import com.Parking.GestionParking.entities.SubscriptionHistory;
import com.Parking.GestionParking.repository.ISubscriptionHistoryRepository;
import com.Parking.GestionParking.repository.ISubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GestionSubscriptionImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepo;

    @Mock
    private ISubscriptionHistoryRepository subscriptionHistoryRepository;

    @InjectMocks
    private GestionSubscriptionImpl gestionSubscription;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscription = new Subscription();
        subscription.setIdSub(1L);
        subscription.setSubscriptionHistories(new ArrayList<>()); // Initialiser la liste ici
    }



    @Test
    void testRetrieveAllSubscription() {
        when(subscriptionRepo.findAll()).thenReturn(Arrays.asList(subscription));

        List<Subscription> result = gestionSubscription.retrieveAllSubscription();

        assertEquals(1, result.size());
        verify(subscriptionRepo, times(1)).findAll();
    }

    @Test
    void testAddSubscription() {
        when(subscriptionRepo.save(any(Subscription.class))).thenReturn(subscription);

        Subscription result = gestionSubscription.addSubscription(subscription);

        assertEquals(subscription, result);
        verify(subscriptionRepo, times(1)).save(subscription);

        // Vérifiez que l'historique d'abonnement est enregistré
        ArgumentCaptor<SubscriptionHistory> captor = ArgumentCaptor.forClass(SubscriptionHistory.class);
        verify(subscriptionHistoryRepository, times(1)).save(captor.capture());
        assertEquals("CREATED", captor.getValue().getAction());
        assertEquals(subscription, captor.getValue().getSubscription());
    }

    @Test
    void testUpdateSubscription() {
        when(subscriptionRepo.save(any(Subscription.class))).thenReturn(subscription);

        Subscription result = gestionSubscription.updateSubscription(subscription);

        assertEquals(subscription, result);
        verify(subscriptionRepo, times(1)).save(subscription);

        // Vérifiez que l'historique d'abonnement est enregistré
        ArgumentCaptor<SubscriptionHistory> captor = ArgumentCaptor.forClass(SubscriptionHistory.class);
        verify(subscriptionHistoryRepository, times(1)).save(captor.capture());
        assertEquals("UPDATED", captor.getValue().getAction());
        assertEquals(subscription, captor.getValue().getSubscription());
    }

    @Test
    void testRetrieveSubscription() {
        when(subscriptionRepo.findById(1L)).thenReturn(Optional.of(subscription));

        Subscription result = gestionSubscription.retrieveSubscription(1L);

        assertEquals(subscription, result);
        verify(subscriptionRepo, times(1)).findById(1L);
    }

    @Test
    void testDeleteSubscription() {
        when(subscriptionRepo.findById(1L)).thenReturn(Optional.of(subscription));

        gestionSubscription.deleteSubscription(1L);

        verify(subscriptionRepo, times(1)).findById(1L);
        verify(subscriptionRepo, times(1)).delete(subscription);
    }

    @Test
    void testDeleteSubscription_NotFound() {
        when(subscriptionRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionSubscription.deleteSubscription(1L);
        });

        assertEquals("Subscription not found with id: 1", exception.getMessage());
        verify(subscriptionRepo, times(1)).findById(1L);
    }
}

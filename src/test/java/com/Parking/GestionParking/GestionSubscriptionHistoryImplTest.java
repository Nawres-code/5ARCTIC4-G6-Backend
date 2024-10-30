package com.Parking.GestionParking;
import com.Parking.GestionParking.services.GestionSubscriptionImpl;
import com.Parking.GestionParking.services.GestionSubscriptionHistoryImpl;
import com.Parking.GestionParking.entities.Subscription;
import com.Parking.GestionParking.entities.SubscriptionHistory;
import com.Parking.GestionParking.repository.ISubscriptionHistoryRepository;
import com.Parking.GestionParking.repository.ISubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestionSubscriptionHistoryImplTest {

    @Mock
    private ISubscriptionHistoryRepository subhistoryRepo;

    @Mock
    private ISubscriptionRepository subscriptionRepo;

    @InjectMocks
    private GestionSubscriptionHistoryImpl gestionSubscriptionHistory;

    private Subscription subscription;
    private SubscriptionHistory subscriptionHistory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscription = new Subscription();
        subscription.setIdSub(1L); // Utilisez setIdSub ici

        subscriptionHistory = new SubscriptionHistory();
        subscriptionHistory.setIdSubHis(1); // Remplacez par le setter approprié si nécessaire
        subscriptionHistory.setNumSubHistory(1L);
        subscriptionHistory.setAction("CREATED");
        subscriptionHistory.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testAssignHistoryToSubscription() {
        Long numSubHistory = 1L;
        Long numSubscription = 1L;

        // Mock repository behavior
        when(subscriptionRepo.findById(numSubscription)).thenReturn(Optional.of(subscription));
        when(subhistoryRepo.findById(numSubHistory)).thenReturn(Optional.of(subscriptionHistory));
        when(subhistoryRepo.save(subscriptionHistory)).thenReturn(subscriptionHistory);
        // Call the method
        SubscriptionHistory result = gestionSubscriptionHistory.assignHistoryToSubscription(numSubHistory, numSubscription);
        // Assertions
        assertNotNull(result); // This should pass
        assertEquals(numSubHistory, result.getNumSubHistory());
    }



    @Test
    void testRetrieveAllSubscriptionHistory() {
        when(subhistoryRepo.findAll()).thenReturn(Arrays.asList(subscriptionHistory));

        List<SubscriptionHistory> result = gestionSubscriptionHistory.retrieveAllSubscriptionHistory();

        assertEquals(1, result.size());
        verify(subhistoryRepo, times(1)).findAll();
    }

    @Test
    void testAssignHistoryToSubscription_SubscriptionNotFound() {
        Long numSubHistory = 1L;
        Long numSubscription = 1L;

        when(subscriptionRepo.findById(numSubscription)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            gestionSubscriptionHistory.assignHistoryToSubscription(numSubHistory, numSubscription);
        });

        assertEquals("No value present", exception.getMessage());
        verify(subhistoryRepo, never()).save(any());
    }

    @Test
    void testAssignHistoryToSubscription_HistoryNotFound() {
        Long numSubHistory = 1L;
        Long numSubscription = 1L;

        when(subscriptionRepo.findById(numSubscription)).thenReturn(Optional.of(subscription));
        when(subhistoryRepo.findById(numSubHistory)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            gestionSubscriptionHistory.assignHistoryToSubscription(numSubHistory, numSubscription);
        });

        assertEquals("No value present", exception.getMessage());
        verify(subhistoryRepo, never()).save(any());
    }
}

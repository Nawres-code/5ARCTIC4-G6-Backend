package com.Parking.GestionParking;


import com.Parking.GestionParking.repository.ReservationRepository;
import com.Parking.GestionParking.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    public void testGetReservationCountSinceLastWeek() {
        // Arrange
        Date startDateOfLastWeek = Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Mocking the behavior of the repository method
        when(reservationRepository.countReservationsSinceLastWeek(startDateOfLastWeek)).thenReturn(5L);

        // Act
        Long count = reservationService.getReservationCountSinceLastWeek();

        // Assert
        assertEquals(5L, count); // Verify that the expected count matches the actual count
    }
}

package com.Parking.GestionParking.repository;


import com.Parking.GestionParking.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    // You can add custom query methods here if needed
}
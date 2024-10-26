package com.Parking.GestionParking.controller;


import com.Parking.GestionParking.entities.SubscriptionHistory;
import com.Parking.GestionParking.services.IGestionSubscriptionHistory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subHistory")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriptionHistoryController {
    IGestionSubscriptionHistory subhistoryService;
    // @PostMapping("/assignCours/{numI}/{numC}")
    /// public assignHistoryToSubscription(@PathVariable("numI") Long numInsc, @PathVariable("numC") Long numCours) {
    ///return inscService.assignInscriptionToCours(numInsc, numCours);
    @GetMapping("/getAll")
    public List<SubscriptionHistory> getAll(){
        return subhistoryService.retrieveAllSubscriptionHistory();
    }
}



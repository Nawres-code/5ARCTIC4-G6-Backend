package com.Parking.GestionParking.controller;


import com.Parking.GestionParking.entities.Subscription;
import com.Parking.GestionParking.services.IGestionSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    IGestionSubscription subscription;
    @GetMapping("/getAll")
    public List<Subscription> getAll(){
        return subscription.retrieveAllSubscription();
    }

    @PostMapping("/add")
    public Subscription addSubscription(@RequestBody Subscription s) {
        return subscription.addSubscription(s);
    }
    @GetMapping("/getSubscription/{idSub}")
    public Subscription getSubscription(@PathVariable("idSub") Long idSub) {
        return subscription.retrieveSubscription(idSub);
    }
    @PutMapping("/edit")
    public Subscription updateSubscription(@RequestBody Subscription s) {
        return subscription.updateSubscription(s);
    }
    @DeleteMapping("/delete/{idSub}")
    public void deleteSubscription(@PathVariable("idSub") Long idSub) {
        subscription.deleteSubscription(idSub);
    }
}

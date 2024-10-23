package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.entities.Reclamation;
import com.Parking.GestionParking.services.IGestionReclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/reclamation")
public class ReclamationControllerImpl {
    @Autowired
    IGestionReclamation iGestionReclamation;

    @GetMapping("/getall")
    public List<Reclamation> getAll(){
        return iGestionReclamation.retrieveAllReclamation();
    }

        @PostMapping("/addReclamation")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation){
        return iGestionReclamation.addReclamation(reclamation);
    }

    @GetMapping("/getReclamationById/{idReclamation}")
    public Reclamation getReclamationById(@PathVariable("idReclamation") Integer idReclamation){
        return iGestionReclamation.retrieveReclamation(idReclamation);
    }
    @PutMapping("/updateReclamation")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation){
        return iGestionReclamation.updateReclamation(reclamation);
    }
    @DeleteMapping("/deleteReclamation")
    public ResponseEntity<Integer> deleteReclamation(@RequestBody Integer idreclamation){
         iGestionReclamation.deleteReclamation(idreclamation);
         return ResponseEntity.ok().body(idreclamation);
    }

    @GetMapping("/getReclamationByEmail/{email}")
    public List<Reclamation> getReclamationByEmail(@PathVariable("email") String email) {
        return iGestionReclamation.getReclamationByEmail(email);
    }

    @PutMapping("/accept/{email}")
    public Reclamation acceptReclamationByEmail(@PathVariable String email) {
        return iGestionReclamation.acceptReclamationByEmail(email);
    }

    @PutMapping("/reject/{email}")
    public Reclamation rejectReclamationByEmail(@PathVariable String email) {
        return iGestionReclamation.RejectReclamationByEmail(email);
    }

}

package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.services.IGestionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('SimpleCLient')")
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
//@PreAuthorize("hasRole('SimpleCLient')")

public class UserController {
    @Autowired
    IGestionUser gestionUser;

    @GetMapping("/getall")
    public List<User> getAll(){
        return gestionUser.retrieveAlluser();
    }

    @GetMapping("/getUserId/{id}")
    public User getUserId(@PathVariable("id") Integer id){
        return gestionUser.retrieveUser(id);
    }

    @DeleteMapping("deleteId/{id}")
    public void delete(@PathVariable("id") Integer id){
        gestionUser.removeUser(id);
    }

    @PutMapping("/updateuser")
    public User updateuser(@RequestBody User user){
        return gestionUser.updateUser(user) ;
    }

}
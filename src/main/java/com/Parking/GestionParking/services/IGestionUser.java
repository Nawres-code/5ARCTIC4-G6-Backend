package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.User;

import java.util.List;

public interface IGestionUser {
   // User addUser(User user);
    List<User> retrieveAlluser();
    User updateUser(User user);
    User retrieveUser (Integer id);
    void removeUser (Integer id);
}

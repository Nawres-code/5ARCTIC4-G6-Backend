package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GestionUserImpl implements IGestionUser{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> retrieveAlluser() {
        return (List<User>)  userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        //userRepository.delete(user);
        return userRepository.save(user);
    }

    @Override
    public User retrieveUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void removeUser(Integer id) {
        userRepository.deleteById(id);

    }
}
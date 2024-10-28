package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.Utils.DateUtils;
import com.Parking.GestionParking.entities.Role;
import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.UserRepository;
import com.Parking.GestionParking.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/userdashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDashboardController {

    private final UserRepository userRepository;
    private final ReservationService reservationService;

    @Autowired
    public UserDashboardController(UserRepository userRepository, ReservationService reservationService) {

        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @GetMapping("/user-count")
    public ResponseEntity<Long> getUserCount() {
        long totalUsers = userRepository.getTotalUserCount();
        // long subscribedUsers = userRepository.getSubscribedUserCount();
        //  long simpleUsers = userRepository.getSimpleUserCount();

        //  Map<String, Long> counts = new HashMap<>();
        //  counts.put("totalUsers", totalUsers);
        // counts.put("subscribedUsers", subscribedUsers);
        //  counts.put("simpleUsers", simpleUsers);

        return ResponseEntity.ok(totalUsers);



    }

    @GetMapping("/subscribed-user-count")
    public ResponseEntity<Long> getSubscribedUserCount() {
        long subscribedUsers = userRepository.getSubscribedUserCount();
        return ResponseEntity.ok(subscribedUsers);
    }

    @GetMapping("/simple-user-count")
    public ResponseEntity<Long> getSimpleUserCount() {
        long simpleUsers = userRepository.getSimpleUserCount();
        return ResponseEntity.ok(simpleUsers);
    }

    @GetMapping("/user-percentages")
    public ResponseEntity<Map<String, Double>> getUserPercentages() {
        long totalUsers = userRepository.getTotalUserCount();
        long subscribedUsers = userRepository.getSubscribedUserCount();
        long simpleUsers = userRepository.getSimpleUserCount();

        double subscribedPercentage = (double) subscribedUsers / totalUsers * 100;
        double simplePercentage = (double) simpleUsers / totalUsers * 100;

        Map<String, Double> percentages = new HashMap<>();
        percentages.put("sub" +
                "scribedPercentage", subscribedPercentage);
        percentages.put("simplePercentage", simplePercentage);

        return ResponseEntity.ok(percentages);
    }

    @GetMapping("/most-active-user")
    public ResponseEntity<String> getMostActiveUserFullName() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Object[]> mostActiveUsers = userRepository.findMostActiveUsers(pageable);
        if (!mostActiveUsers.isEmpty()) {
            Object[] mostActiveUser = mostActiveUsers.get(0);
            String fullName = (String) mostActiveUser[0]; // Full name is at index 0
            return ResponseEntity.ok(fullName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/user-count-since-last-month")
    public ResponseEntity<Long> getUserCountSinceLastMonth() {
        // Calculate the start date of the previous month
        LocalDateTime startDateOfPreviousMonth = DateUtils.getStartDateOfPreviousMonth();

        // Count users added since the start date of the previous month
        long usersAddedSinceLastMonth = userRepository.countUsersAddedSince(startDateOfPreviousMonth);

        return ResponseEntity.ok(usersAddedSinceLastMonth);
    }

    @GetMapping("/reservation-count-since-last-week")
    public ResponseEntity<Long> getReservationCountSinceLastWeek() {
        Long count = reservationService.getReservationCountSinceLastWeek();
        return ResponseEntity.ok(count);
    }

    /*@GetMapping("/subscribed-user-count")
    public ResponseEntity<Long> getSubscribedUserCount() {
        long subscribedUsers = userRepository.getUserCountByRole(String.valueOf(Role.SubscribedClient));
        return ResponseEntity.ok(subscribedUsers);
    }

    @GetMapping("/simple-user-count")
    public ResponseEntity<Long> getSimpleUserCount() {
        long simpleUsers = userRepository.getUserCountByRole(String.valueOf(Role.SimpleClient));
        return ResponseEntity.ok(simpleUsers);
    }*/


}
package com.Parking.GestionParking.repository;

import com.Parking.GestionParking.entities.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedbackRepository extends CrudRepository<Feedback,Integer> {
}

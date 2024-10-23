package com.Parking.GestionParking.repository;

import com.Parking.GestionParking.entities.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICommentRepository extends CrudRepository<Comments,Integer> {
    List<Comments> findAllByOrderByDatePubDesc();
}

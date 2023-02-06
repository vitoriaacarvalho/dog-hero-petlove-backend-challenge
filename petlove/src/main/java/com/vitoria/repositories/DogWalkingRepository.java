package com.vitoria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vitoria.enums.Status;
import com.vitoria.models.DogWalking;

public interface DogWalkingRepository extends JpaRepository<DogWalking, Integer>{

	default List<DogWalking> findAllWhereStatusIsOpen() {
        return findByStatus(Status.OPEN);
    }
	
	List<DogWalking> findByStatus(Status status);

}

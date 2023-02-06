package com.vitoria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitoria.models.DogWalking;

public interface DogWalkingRepository extends JpaRepository<DogWalking, Integer>{
	@Query(value="SELECT x FROM DogWalking x WHERE x.status = 'OPEN'")
	public List<DogWalking> findByStatus();
}

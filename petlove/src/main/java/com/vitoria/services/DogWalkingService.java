package com.vitoria.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vitoria.enums.Status;
import com.vitoria.exceptions.ResponseStatusException;
import com.vitoria.models.DogWalking;
import com.vitoria.repositories.DogWalkingRepository;

@Service
public class DogWalkingService {
	
	@Autowired
	private DogWalkingRepository repo;

	public Optional<DogWalking> findById(Integer id) {
		Optional<DogWalking> walk=repo.findById(id);
		if (walk==null) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, ("There's no walk registered under this id"));
		}
		return walk;
	}
	
	public DogWalking checkIdAndStartingTime(Integer id) {
		DogWalking walk=repo.findById(id).get();
		if (walk==null  || walk.getWalkStartingTime()==null) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, ("This walk cannot be finished. It either does not exist or wasn't even started yet."));
		}
		return walk;
	}
	public DogWalking addingNewWalk(DogWalking walk) {
		walk.setSchedulingDate(LocalDate.now());
		walk.setStatus(Status.OPEN);
		return walk; 
	}
}

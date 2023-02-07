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
	
	public DogWalking checkIdStartingTimeAndFinishingTime(Integer id) {
		DogWalking walk=repo.findById(id).get();
		if (walk==null  || walk.getWalkStartingTime()==null || walk.getWalkFinishingTime()!=null ) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, ("This walk cannot be finished, either because it does not exist, it wasn't even started or it has already been closed."));
		}
		return walk;
	}
	public DogWalking addingNewWalk(DogWalking walk) {
		walk.setSchedulingDate(LocalDate.now());
		walk.setStatus(Status.OPEN);
		return walk; 
	}
	
	public DogWalking checkingIfWalkCanBeStarted(DogWalking walk) {
		if(walk.getWalkStartingTime()!=null) {
			throw new ResponseStatusException(
			           HttpStatus.CONFLICT, ("This walk cannot be started right now because it has already been started."));
		}
		return walk;
	}
	
	//
	public DogWalking checkingIfWalkCanBeFinished(Integer id) {
		DogWalking walk=repo.findById(id).get();
		if(walk.getWalkFinishingTime()!=null) {
			throw new ResponseStatusException(
			           HttpStatus.CONFLICT, ("This walk cannot be finished right now because it has already been finished."));
		}
		return walk;
	}
	
	
	
	
	
	
}

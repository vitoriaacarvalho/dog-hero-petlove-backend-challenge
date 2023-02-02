package com.vitoria.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoria.exceptions.ResponseStatusException;
import com.vitoria.models.DogWalking;
import com.vitoria.repositories.DogWalkingRepository;

@RestController
@RequestMapping("/walks")
public class DogWalkingController {

	@Autowired
	private DogWalkingRepository repo;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	@PostMapping("/insert")
	public ResponseEntity<DogWalking> create(@RequestBody DogWalking dogWalking){
		dogWalking.setSchedulingDate(LocalDate.now());
		DogWalking entity=repo.save(dogWalking);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DogWalking>> findAll(){
		List<DogWalking> walks=repo.findAll();
		return ResponseEntity.ok().body(walks);
	}
	
	@GetMapping("/show/{id}")
	public ResponseEntity<Optional<DogWalking>> findById(@PathVariable Integer id){
		Optional<DogWalking> walk=repo.findById(id);
		if (walk==null) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, ("There's no walk registered under this id"));
		}
		return ResponseEntity.ok().body(walk);
	}
	
	@GetMapping("/start_walk")
	public ResponseEntity<String> startWalk(DogWalking dogWalk){
		DogWalking entity=repo.findById(dogWalk.getId()).get();
		if (entity==null) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, ("There's no walk registered under this id"));
		}
		dogWalk.setWalkStartingTime(LocalDateTime.now());
		return ResponseEntity.ok().body("Walk successfully started!");
		
	}

	@GetMapping("/finish_walk")
	public ResponseEntity<String> finishWalk(DogWalking dogWalk){
		DogWalking entity=repo.findById(dogWalk.getId()).get();
		if (entity==null || entity.getWalkStartingTime()==null) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, ("This walk cannot be finished. It either does not exist or wasn't even started yet."));
		}
		dogWalk.setWalkFinishingTime(LocalDateTime.now());
		dogWalk.setDuration(Long.toString(gettingRealDuration(dogWalk.getWalkStartingTime(),dogWalk.getWalkFinishingTime())));
		return ResponseEntity.ok().body("Walk successfully finished!");
		
	}
	
	
	private static long gettingRealDuration(LocalDateTime startingTime, LocalDateTime finishingTime) {
		long diffInMilli = java.time.Duration.between(startingTime, finishingTime).toMillis();
		long diffInSeconds = java.time.Duration.between(startingTime, finishingTime).getSeconds();
		long diffInMinutes = java.time.Duration.between(startingTime, finishingTime).toMinutes();
		return diffInMinutes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

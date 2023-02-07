package com.vitoria.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoria.enums.Status;
import com.vitoria.exceptions.ResponseStatusException;
import com.vitoria.models.DogWalking;
import com.vitoria.repositories.DogWalkingRepository;
import com.vitoria.services.DogWalkingService;

@RestController
@RequestMapping("/walks")
public class DogWalkingController {

	@Autowired
	private DogWalkingRepository repo;
	
	@Autowired 
	private DogWalkingService service;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	@PostMapping("/insert")
	public ResponseEntity<DogWalking> create(@RequestBody DogWalking dogWalking){
		DogWalking entity=service.addingNewWalk(dogWalking);
		entity.setStatus(Status.OPEN);
		repo.save(entity);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping("/all/index")
	public ResponseEntity<List<DogWalking>> findAllOpen(){
		List<DogWalking> walks=repo.findAllWhereStatusIsOpen();
		if(walks==null) {
			throw new ResponseStatusException(
			           HttpStatus.NO_CONTENT, ("There are no opened walks right now"));
		}
		return ResponseEntity.ok().body(walks);
	}

	@GetMapping("/all-walks")
	public ResponseEntity<List<DogWalking>> findAll(){
		List<DogWalking> walks=repo.findAll();
		if(walks==null) {
			throw new ResponseStatusException(
			           HttpStatus.NO_CONTENT, ("There are no walks registered"));
		}
		return ResponseEntity.ok().body(walks);
	}
	
	
	@GetMapping("/show/{id}")
	public ResponseEntity<Optional<DogWalking>> findById(@PathVariable Integer id){
		Optional<DogWalking> walk=service.findById(id);
		return ResponseEntity.ok().body(walk);
	}
	
	
	@PutMapping("/start_walk/{id}")
	public ResponseEntity<String> startWalk(@PathVariable Integer id){
		DogWalking dogWalk=service.findById(id).get();
		service.checkingIfWalkCanBeStarted(dogWalk);
		dogWalk.setWalkStartingTime(LocalDateTime.now());
		dogWalk.setStatus(Status.IN_MOTION);
		repo.save(dogWalk);
		return ResponseEntity.ok().body("Walk successfully started!");
		
	}

	@PutMapping("/finish_walk/{id}")
	public ResponseEntity<String> finishWalk(@PathVariable Integer id){
		DogWalking dogWalk=service.checkIdStartingTimeAndFinishingTime(id);
		dogWalk.setWalkFinishingTime(LocalDateTime.now());
		dogWalk.setDuration(Long.toString(gettingRealDuration(dogWalk.getWalkStartingTime(),dogWalk.getWalkFinishingTime())));
		dogWalk.setStatus(Status.CLOSED);
		repo.save(dogWalk);
		return ResponseEntity.ok().body("Walk successfully finished!");
	}
	
	//this one HAS to be available for users
	@PutMapping("/cancel-walk/{id}")
	public ResponseEntity<String> cancelWalk(@PathVariable Integer id){
		DogWalking walk=repo.findById(id).get();
		walk.setStatus(Status.CANCELED);
		repo.save(walk);
		return ResponseEntity.ok().body("This walk was sucessfully canceled!");
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<DogWalking> update(@PathVariable Integer id,@RequestBody DogWalking walk){
		DogWalking updatedWalk=repo.findById(id).get();
		updatedWalk.setLatitude(walk.getLatitude());
		updatedWalk.setLongitude(walk.getLongitude());
		updatedWalk.setDuration(walk.getDuration());
		updatedWalk.setTimeStamp(walk.getTimeStamp());
		repo.save(updatedWalk);
		return ResponseEntity.ok().body(updatedWalk);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete-all")
	public ResponseEntity<Void> deleteAll(){
		repo.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
	private static long gettingRealDuration(LocalDateTime startingTime, LocalDateTime finishingTime) {
		//long diffInMilli = java.time.Duration.between(startingTime, finishingTime).toMillis();
		//long diffInSeconds = java.time.Duration.between(startingTime, finishingTime).getSeconds();
		long diffInMinutes = java.time.Duration.between(startingTime, finishingTime).toMinutes();
		return diffInMinutes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.vitoria.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoria.models.Owners;
import com.vitoria.models.Pets;
import com.vitoria.repositories.PetsRepository;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
public class PetsController {

	@Autowired
	private PetsRepository repo;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/insert") 
	public ResponseEntity<Pets> insert(@RequestBody Pets pet){
		repo.save(pet);
		return ResponseEntity.ok().body(pet);
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/all")
	public ResponseEntity<List<Pets>> findAll(){
		List<Pets> pets=repo.findAll();
		return ResponseEntity.ok().body(pets);	
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	public ResponseEntity<Optional<Pets>> findById(@PathVariable Integer id){
		Optional<Pets> pet=repo.findById(id);
		return ResponseEntity.ok().body(pet);
	}
	@GetMapping("find-owner/{id}")
	public ResponseEntity<String> findPetsOwner(@PathVariable Integer id){
		Pets pet=repo.findById(id).get();
		String owner=pet.getOwner().toString();
		return ResponseEntity.ok().body(owner);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Pets> update(@PathVariable Integer id, @RequestBody Pets pet){
		Pets updatedPet=repo.findById(id).get();
		
		updatedPet.setName(pet.getName());
		updatedPet.setBreed(pet.getBreed());
		updatedPet.setBirthDate(pet.getBirthDate());
		updatedPet.setOwner(pet.getOwner());
		
		repo.save(updatedPet);
		return ResponseEntity.ok().body(updatedPet);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

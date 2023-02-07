package com.vitoria.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoria.models.Owners;
import com.vitoria.repositories.OwnersRepository;

@RestController
@RequestMapping("/owners")
public class OwnersController {
	
	@Autowired
	private OwnersRepository repo;
	
	@PostMapping("/insert") 
	public ResponseEntity<Owners> insert(@RequestBody Owners owner){
		repo.save(owner);
		return ResponseEntity.ok().body(owner);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Owners>> findAll(){
		List<Owners> owners=repo.findAll();
		return ResponseEntity.ok().body(owners);	
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Optional<Owners>> findById(@PathVariable Integer id){
		Optional<Owners> owner=repo.findById(id);
		return ResponseEntity.ok().body(owner);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Owners> update(@PathVariable Integer id, @RequestBody Owners owner){
		Owners updatedOwner=repo.findById(id).get();
		updatedOwner.setAddress(owner.getAddress());
		updatedOwner.setEmail(owner.getEmail());
		updatedOwner.setPhoneNumber(owner.getPhoneNumber());
		updatedOwner.setName(owner.getName());
		repo.save(updatedOwner);
		return ResponseEntity.ok().body(updatedOwner);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}

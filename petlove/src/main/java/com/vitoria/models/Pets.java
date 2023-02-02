package com.vitoria.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="pets_table")
public class Pets {
	private String name;
	private String breed;
	private Date birthDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owners owner;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToMany(mappedBy="id")
	private List<DogWalking> walks;
	

	public Pets() {
		
	}
	
	public Pets(String name, String breed, Date birthDate,  Owners owner , Integer id,List<DogWalking> walks) {
		this.name = name;
		this.breed = breed;
		this.birthDate = birthDate;
		this.owner = owner;
		this.id = id;
		this.walks=walks;
	}

	/*@JsonProperty("flight_id") private void unpackNested(Integer flight_id) {
	    this.flight = new Flight(); flight.setFlight_id(flight_id);
	}*/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Owners getOwner() {
		return owner;
	}

	public void setOwner(Owners owner) {
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DogWalking> getWalks() {
		return walks;
	}

	public void setWalks(List<DogWalking> walks) {
		this.walks = walks;
	}
	
}

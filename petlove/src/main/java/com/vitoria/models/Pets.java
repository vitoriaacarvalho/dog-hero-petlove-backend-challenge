package com.vitoria.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	
	public Pets() {
		
	}
	
	public Pets(String name, String breed, Date birthDate, Owners owner, Integer id) {
		
		this.name = name;
		this.breed = breed;
		this.birthDate = birthDate;
		this.owner = owner;
		this.id = id;
	}

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
	
}

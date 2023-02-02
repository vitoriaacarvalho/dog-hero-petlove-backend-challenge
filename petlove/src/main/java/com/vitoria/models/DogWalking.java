package com.vitoria.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vitoria.enums.Status;
import com.vitoria.enums.TimeStamp;

@Entity
@Table(name="dog_walking")
public class DogWalking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate schedulingDate;
	
	@Column(nullable=true)
	private Status status;
	
	private TimeStamp timeStamp;
	
	private String latitude;
	
	private String longitude;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pets pet;
	
	private LocalDateTime walkStartingTime;
	
	private LocalDateTime walkFinishingTime;
	private String duration;
	
	
	public DogWalking() {
	}

	public DogWalking(LocalDate schedulingDate, Status status, TimeStamp timeStamp, String latitude, String longitude,
			Pets pet,LocalDateTime walkStartingTime, LocalDateTime walkFinishingTime, Integer id, String duration) {
		this.schedulingDate = schedulingDate;
		this.status = status;
		this.timeStamp = timeStamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pet = pet;
		this.walkStartingTime = walkStartingTime;
		this.walkFinishingTime = walkFinishingTime;
		this.id = id;
		this.duration=duration;
	}

	public LocalDate getSchedulingDate() {
		return schedulingDate;
	}

	public void setSchedulingDate(LocalDate schedulingDate) {
		this.schedulingDate = schedulingDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public LocalDateTime getWalkStartingTime() {
		return walkStartingTime;
	}

	public void setWalkStartingTime(LocalDateTime walkStartingTime) {
		this.walkStartingTime = walkStartingTime;
	}

	public LocalDateTime getWalkFinishingTime() {
		return walkFinishingTime;
	}

	public void setWalkFinishingTime(LocalDateTime walkFinishingTime) {
		this.walkFinishingTime = walkFinishingTime;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}

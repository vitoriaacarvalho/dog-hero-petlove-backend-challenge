package com.vitoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitoria.models.DogWalking;

public interface OwnersRepository extends JpaRepository<DogWalking, Integer>{

}

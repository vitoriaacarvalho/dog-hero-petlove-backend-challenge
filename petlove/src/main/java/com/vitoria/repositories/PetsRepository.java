package com.vitoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitoria.models.Pets;

public interface PetsRepository extends JpaRepository<Pets, Integer>{

}

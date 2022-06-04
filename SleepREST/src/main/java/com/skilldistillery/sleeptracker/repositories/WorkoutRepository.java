package com.skilldistillery.sleeptracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.sleeptracker.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{

}

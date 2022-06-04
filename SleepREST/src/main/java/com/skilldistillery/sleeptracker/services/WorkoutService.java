package com.skilldistillery.sleeptracker.services;

import java.util.List;

import com.skilldistillery.sleeptracker.entities.Workout;

public interface WorkoutService {

	Workout findById(int id);
	
	List<Workout> findAll();
	
}

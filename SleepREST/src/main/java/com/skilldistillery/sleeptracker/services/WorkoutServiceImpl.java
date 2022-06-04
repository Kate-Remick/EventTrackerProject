package com.skilldistillery.sleeptracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.sleeptracker.entities.Workout;
import com.skilldistillery.sleeptracker.repositories.WorkoutRepository;

@Service
public class WorkoutServiceImpl implements WorkoutService{

	@Autowired
	private WorkoutRepository wr;

	@Override
	public Workout findById(int id) {
		Workout wkt = null;
		Optional<Workout> op = wr.findById(id);
		if(op.isPresent()) {
			wkt = op.get();
		}
		return wkt;
	}

	@Override
	public List<Workout> findAll() {
		return wr.findAll();
	}
	
	
	
	
}

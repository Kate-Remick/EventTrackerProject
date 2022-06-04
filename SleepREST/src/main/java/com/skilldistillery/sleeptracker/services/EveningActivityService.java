package com.skilldistillery.sleeptracker.services;

import java.util.List;

import com.skilldistillery.sleeptracker.entities.EveningActivity;

public interface EveningActivityService {

	EveningActivity createActivity(EveningActivity ea);
	
	EveningActivity findById(int id);
	
	List<EveningActivity> findAll();
	
	
	
}

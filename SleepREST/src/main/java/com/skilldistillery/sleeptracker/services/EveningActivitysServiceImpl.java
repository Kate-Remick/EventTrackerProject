package com.skilldistillery.sleeptracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.repositories.EveningActivityRepository;

@Service
public class EveningActivitysServiceImpl implements EveningActivityService{

	@Autowired
	private EveningActivityRepository ear;

	@Override
	public EveningActivity createActivity(EveningActivity ea) {
		return ear.saveAndFlush(ea);
	}

	@Override
	public EveningActivity findById(int id) {
		EveningActivity ea = null;
		Optional<EveningActivity> op = ear.findById(id);
		if(op.isPresent()) {
			ea = op.get();
		}
		return ea;
	}

	@Override
	public List<EveningActivity> findAll() {
		return ear.findAll();
	}
	
	
	
}

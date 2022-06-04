package com.skilldistillery.sleeptracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.sleeptracker.entities.SleepPeriod;
import com.skilldistillery.sleeptracker.repositories.EveningActivityRepository;
import com.skilldistillery.sleeptracker.repositories.SleepPeriodRepository;
import com.skilldistillery.sleeptracker.repositories.WorkoutRepository;

@Service
public class SleepPeriodServiceImpl implements SleepPeriodService{

	@Autowired
	private SleepPeriodRepository spr;
	
	@Autowired
	private WorkoutRepository wr;
//	
//	@Autowired
//	private EveningActivityRepository ear;

	@Override
	public SleepPeriod findById(int id) {
		SleepPeriod sp = null;
		Optional<SleepPeriod> op = spr.findById(id);
		if(op.isPresent()) {
			sp = op.get();
		}
		return sp;
	}

	@Override
	public List<SleepPeriod> findAll() {
		return spr.findAll();
	}

	@Override
	public SleepPeriod addSleep(SleepPeriod sp) {
		sp = spr.saveAndFlush(sp);
		
		return sp;
	}

	@Override
	public boolean removeSleep(int id) {
		boolean removed;
		spr.deleteById(id);
		removed = !spr.existsById(id);
		return removed;
	}

	@Override
	public List<SleepPeriod> findByWorkoutTime(int wktId) {
		return spr.findByWorkout_IdEquals(wktId);
	}

	@Override
	public List<SleepPeriod> findByEveningActivity(int eaId) {
		return spr.findByEveningActivity_IdEquals(eaId);
	}

	@Override
	public List<SleepPeriod> findByQualityGroup(int low, int high) {
		return spr.findByQualityGroup(low, high);
	}
	
	
	
}

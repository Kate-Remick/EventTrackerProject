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
	public SleepPeriod editSleep(SleepPeriod sp, int id) {
		SleepPeriod edited =  null;
		Optional<SleepPeriod> op = spr.findById(id);
		if(op.isPresent()) {
			edited = op.get();
			edited.setDuration(sp.getDuration());
			edited.setEnd(sp.getEnd());
			edited.setEveningActivity(sp.getEveningActivity());
			edited.setExcercised(sp.isExcercised());
			edited.setHadAlcohol(sp.isHadAlcohol());
			edited.setLargeDinner(sp.isLargeDinner());
			edited.setQuality(sp.getQuality());
			edited.setStart(sp.getStart());
			edited.setTookNap(sp.isTookNap());
			edited.setWorkout(sp.getWorkout());
			edited = spr.saveAndFlush(edited);
		}
		return edited;
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

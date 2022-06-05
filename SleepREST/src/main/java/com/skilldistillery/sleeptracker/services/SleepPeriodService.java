package com.skilldistillery.sleeptracker.services;

import java.util.List;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.entities.SleepPeriod;
import com.skilldistillery.sleeptracker.entities.Workout;


public interface SleepPeriodService {

	SleepPeriod findById(int id);
	
	List<SleepPeriod> findAll();
	
	SleepPeriod addSleep(SleepPeriod sp);
	
	SleepPeriod editSleep(SleepPeriod sp, int id);
	
	boolean removeSleep(int id);
	
	List<SleepPeriod> findByWorkoutTime(int wktId);
	
	List<SleepPeriod> findByEveningActivity(int eaId);
	
	List<SleepPeriod> findByQualityGroup(int low, int high);
	
	
	
	
	
}

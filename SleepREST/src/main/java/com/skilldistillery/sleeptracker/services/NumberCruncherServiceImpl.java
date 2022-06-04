package com.skilldistillery.sleeptracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.entities.SleepPeriod;
import com.skilldistillery.sleeptracker.entities.Workout;
import com.skilldistillery.sleeptracker.repositories.EveningActivityRepository;
import com.skilldistillery.sleeptracker.repositories.SleepPeriodRepository;
import com.skilldistillery.sleeptracker.repositories.WorkoutRepository;

@Service
public class NumberCruncherServiceImpl implements NumberCruncherService{

	@Autowired
	private EveningActivityRepository ear;
	
	@Autowired
	private WorkoutRepository wr;
	
	@Autowired
	private SleepPeriodRepository spr;
	
	
	@Override
	public List<EveningActivity> eveningActivityNonRecommendations() {
		List<EveningActivity> recommended = new ArrayList<>();
		List<EveningActivity> allActivities = ear.findAll();
		double [] averages = new double[allActivities.size()];
		for (int i = 0; i< allActivities.size(); i ++) {
			int id = allActivities.get(i).getId();
			List<SleepPeriod> sleeps = spr.findByEveningActivity_IdEquals(id);
			int sum = 0;
			if(sleeps.size() >= 3) {
				for (SleepPeriod sleep : sleeps) {
					sum += sleep.getQuality();
				}
				double average = sum/sleeps.size();
				averages[i] = average;
			}
		}
		for(int i = 0; i < averages.length; i ++) {
			if(averages[i] >= 7) {
				recommended.add(allActivities.get(i));
			}
		}
		return recommended;
	}

	@Override
	public List<EveningActivity> eveningActivityRecommendations() {
		List<EveningActivity> notRecommended = new ArrayList<>();
		List<EveningActivity> allActivities = ear.findAll();
		double [] averages = new double[allActivities.size()];
		for (int i = 0; i< allActivities.size(); i ++) {
			int id = allActivities.get(i).getId();
			List<SleepPeriod> sleeps = spr.findByEveningActivity_IdEquals(id);
			int sum = 0;
			if(sleeps.size() >= 3) {
				for (SleepPeriod sleep : sleeps) {
					sum += sleep.getQuality();
				}
				double average = sum/sleeps.size();
				averages[i] = average;
			}
		}
		for(int i = 0; i < averages.length; i ++) {
			if(averages[i] <= 3) {
				notRecommended.add(allActivities.get(i));
			}
		}
		return notRecommended;
	}

	@Override
	public Workout workoutTimeRecomendation() {
		List<Workout> allTimes = wr.findAll();
		double [] averages = new double[allTimes.size()];
		for (int i = 0; i< allTimes.size(); i ++) {
			int id = allTimes.get(i).getId();
			List<SleepPeriod> sleeps = spr.findByEveningActivity_IdEquals(id);
			int sum = 0;
			if(sleeps.size() >= 3) {
				for (SleepPeriod sleep : sleeps) {
					sum += sleep.getQuality();
				}
			}
			double average = sum/sleeps.size();
			averages[i] = average;
		}
		double bestQuality = 0;
		Workout bestWorkout = null;
		for(int i = 0; i < averages.length; i ++) {
			if(averages[i] > bestQuality) {
				bestWorkout = allTimes.get(i);
				bestQuality = averages[i];
			}
		}
		return bestWorkout;
	}

	@Override
	public Boolean largeDinnerRecommendation() {
		List<SleepPeriod> allSleeps = spr.findAll();
		int trueSum = 0;
		List<SleepPeriod> trueSleeps = new ArrayList<>();
		int falseSum = 0;
		List<SleepPeriod> falseSleeps = new ArrayList<>();
		for (SleepPeriod sleepPeriod : allSleeps) {
			if(sleepPeriod.isLargeDinner()) {
				trueSum += sleepPeriod.getQuality();
				trueSleeps.add(sleepPeriod);
				
			}else {
				falseSum += sleepPeriod.getQuality();
				falseSleeps.add(sleepPeriod);
			}
		}
		double trueAvg = trueSum/trueSleeps.size();
		double falseAvg = falseSum/falseSleeps.size();
		if(trueSum == 0 || falseSum == 0 || allSleeps.size() < 3 || trueAvg == falseAvg) {
			return null;
		}
		
		return (trueAvg > falseAvg);
	}

	@Override
	public Boolean napRecommendation() {
		List<SleepPeriod> allSleeps = spr.findAll();
		int trueSum = 0;
		List<SleepPeriod> trueSleeps = new ArrayList<>();
		int falseSum = 0;
		List<SleepPeriod> falseSleeps = new ArrayList<>();
		for (SleepPeriod sleepPeriod : allSleeps) {
			if(sleepPeriod.isTookNap()) {
				trueSum += sleepPeriod.getQuality();
				trueSleeps.add(sleepPeriod);
				
			}else {
				falseSum += sleepPeriod.getQuality();
				falseSleeps.add(sleepPeriod);
			}
		}
		double trueAvg = trueSum/trueSleeps.size();
		double falseAvg = falseSum/falseSleeps.size();
		if(trueSum == 0 || falseSum == 0 || allSleeps.size() < 3 || trueAvg == falseAvg) {
			return null;
		}
		
		return (trueAvg > falseAvg);
	}

	@Override
	public String timeStartRecommendation() {
		List<SleepPeriod> allSleeps = spr.findAll();
		List<SleepPeriod> bestTimes = new ArrayList<>();
		int average = 0;
		int sum = 0;
		for (SleepPeriod sleepPeriod : allSleeps) {
			if(sleepPeriod.getQuality() >= 7) {
				bestTimes.add(sleepPeriod);
				sum += sleepPeriod.getStart().getHour();
			}
		}
		average = Math.round(sum/bestTimes.size());
		String result = "Between " + average + ":00 and " + (average + 1) + ":00.";
		
		return result;
	}

	@Override
	public String timeStopRecommendation() {
		List<SleepPeriod> allSleeps = spr.findAll();
		List<SleepPeriod> bestTimes = new ArrayList<>();
		int average = 0;
		int sum = 0;
		for (SleepPeriod sleepPeriod : allSleeps) {
			if(sleepPeriod.getQuality() >= 7) {
				bestTimes.add(sleepPeriod);
				sum += sleepPeriod.getEnd().getHour();
			}
		}
		average = Math.round(sum/bestTimes.size());
		String result = "Between " + average + ":00 and " + (average + 1) + ":00.";
		
		return result;
	}

	@Override
	public String timeElapsedRecommendation() {
		List<SleepPeriod> allSleeps = spr.findAll();
		List<SleepPeriod> bestTimes = new ArrayList<>();
		int average = 0;
		int sum = 0;
		for (SleepPeriod sleepPeriod : allSleeps) {
			if(sleepPeriod.getQuality() >= 7) {
				bestTimes.add(sleepPeriod);
				double elapsed;
				double valueStart = (sleepPeriod.getStart().getHour() + (Math.round((sleepPeriod.getStart().getMinute()/60))*100)/100);
				double valueEnd = (sleepPeriod.getEnd().getHour() + (Math.round((sleepPeriod.getEnd().getMinute()/60))*100)/100);
				if(valueEnd > valueStart) {
					elapsed = valueEnd - valueStart;
				}else {
					elapsed = ((24 - valueStart) + valueEnd);
				}
				sum += elapsed;
			}
		}
		average = Math.round(sum/bestTimes.size());
		String result = "Based on your input so far, you have the best sleep when you have about " + average + " hours of sleep.";
		
		return result;
	}

}

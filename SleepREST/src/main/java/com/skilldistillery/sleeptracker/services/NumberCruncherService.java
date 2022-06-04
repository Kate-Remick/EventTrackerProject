package com.skilldistillery.sleeptracker.services;

import java.util.List;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.entities.Workout;

public interface NumberCruncherService {
	List<EveningActivity> eveningActivityNonRecommendations();

	List<EveningActivity> eveningActivityRecommendations();
	
	Workout workoutTimeRecomendation();

	Boolean largeDinnerRecommendation();

	Boolean napRecommendation();

	String timeStartRecommendation();

	String timeStopRecommendation();

	String timeElapsedRecommendation();
}

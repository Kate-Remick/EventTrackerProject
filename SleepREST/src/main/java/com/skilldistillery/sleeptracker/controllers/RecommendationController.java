package com.skilldistillery.sleeptracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.entities.Workout;
import com.skilldistillery.sleeptracker.services.NumberCruncherService;

@RestController
@RequestMapping("api")
public class RecommendationController {

	@Autowired
	private NumberCruncherService crunch;
	
	
	@GetMapping("recommendations/goodActivities")
	public List <EveningActivity> eveningActivitiesRecommend(HttpServletResponse res){
		List<EveningActivity> recommended = null;
		try {
			recommended = crunch.eveningActivityRecommendations();
			if(recommended == null || recommended.size() == 0)
			{
				res.setStatus(204);
			}
		
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		
		return recommended;
	}
	@GetMapping("recommendations/badActivities")
	public List <EveningActivity> eveningActivitiesNotRecommend(HttpServletResponse res){
		List<EveningActivity> recommended = null;
		try {
			recommended = crunch.eveningActivityNonRecommendations();
			if(recommended == null || recommended.size() == 0)
			{
				res.setStatus(204);
			}
			
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		
		return recommended;
	}
	@GetMapping("recommendations/dinner")
	public String largeDinnerRecommendation(HttpServletResponse res){
		String recommendation = "";
		try {
			if(crunch.largeDinnerRecommendation() == null) {
				recommendation = "Not enough information";
			}else if(crunch.largeDinnerRecommendation()) {
				recommendation = "From the information you have submitted, it looks like you sleep better with a larger dinner";
			}else {
				recommendation = "From the information you have submitted, it looks like you sleep better with a smaller dinner";
				
			}
			
			
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		
		return recommendation;
	}
	@GetMapping("recommendations/nap")
	public String napRecommendation(HttpServletResponse res){
		String recommendation = "";
		try {
			if(crunch.napRecommendation() == null) {
				recommendation = "Not enough information";
			}else if(crunch.napRecommendation()) {
				recommendation = "From the information you have submitted, it looks like naps don't affect your sleep quality";
			}else {
				recommendation = "From the information you have submitted, it looks like you should avoid naps";
				
			}
			
			
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		
		return recommendation;
	}
	@GetMapping("recommendations/workout")
	public Workout workoutRecommendation(HttpServletResponse res){
		Workout recommendation = null;
		try {
			if(crunch.workoutTimeRecomendation() == null) {
				res.setStatus(204);
			}else {
				recommendation = crunch.workoutTimeRecomendation();
				res.setStatus(200);
			}
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		return recommendation;
	}
	@GetMapping("recommendations/sleepStart")
	public String sleepStartRecommendation(HttpServletResponse res){
		String recommendation = null;
		try {
			recommendation = crunch.timeStartRecommendation();
			if(recommendation == null) {
				res.setStatus(404);
			}
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		return recommendation;
	}
	@GetMapping("recommendations/sleepEnd")
	public String sleepEndRecommendation(HttpServletResponse res){
		String recommendation = null;
		try {
			recommendation = crunch.timeStopRecommendation();
			if(recommendation == null) {
				res.setStatus(404);
			}
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		return recommendation;
	}
	@GetMapping("recommendations/duration")
	public String durationRecommendation(HttpServletResponse res){
		String recommendation = null;
		try {
			recommendation = crunch.timeElapsedRecommendation();
			if(recommendation == null) {
				res.setStatus(404);
			}
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		return recommendation;
	}
	
	
	
}

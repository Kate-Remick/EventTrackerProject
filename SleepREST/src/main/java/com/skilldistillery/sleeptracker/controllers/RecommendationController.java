package com.skilldistillery.sleeptracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.entities.Workout;
import com.skilldistillery.sleeptracker.services.NumberCruncherService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})
public class RecommendationController {

	@Autowired
	private NumberCruncherService crunch;
	
	
	@GetMapping("recommendations/all")
	public  List<String> allRecommendations(HttpServletResponse res){
		List<String> allRecommends = new ArrayList<>();
		
		try {
			allRecommends.add(crunch.eveningActivityNonRecommendations());
			allRecommends.add(crunch.eveningActivityRecommendations());
			allRecommends.add(crunch.largeDinnerRecommendation());
			allRecommends.add(crunch.napRecommendation());
			allRecommends.add(crunch.timeElapsedRecommendation());
			allRecommends.add(crunch.timeStartRecommendation());
			allRecommends.add(crunch.timeStopRecommendation());
			allRecommends.add(crunch.workoutTimeRecomendation());
			for(String rec : allRecommends) {
				if(rec == null) {
					res.setStatus(404);
				}
			}
		}catch(Exception e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		return allRecommends;
	}
	
//PieceMeal Recommendations (not currently implemented due to efficiency of retrieve all):
	
//	@GetMapping("recommendations/goodActivities")
//	public String eveningActivitiesRecommend(HttpServletResponse res){
//		List<EveningActivity> recommended = null;
//		try {
//			recommended = crunch.eveningActivityRecommendations();
//			if(recommended == null || recommended.size() == 0)
//			{
//				res.setStatus(204);
//			}
//		
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		
//		String recommendation = "You sleep best after the following activities: ";
//		for (int i = 0; i < recommended.size(); i ++) {
//			recommendation += " " + recommended.get(i).getName();
//			if(i  < recommended.size() - 1) {
//				recommendation += ", ";
//			}
//		}
//		
//		return recommendation;
//	}
//	@GetMapping("recommendations/badActivities")
//	public String eveningActivitiesNotRecommend(HttpServletResponse res){
//		List<EveningActivity> recommended = null;
//		try {
//			recommended = crunch.eveningActivityNonRecommendations();
//			
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		
//		String recommendation = "You should probably avoid these activities before going to bed: ";
//		for (int i = 0; i < recommended.size(); i ++) {
//			recommendation += " " + recommended.get(i).getName();
//			if(i  < recommended.size() - 1) {
//				recommendation += ", ";
//			}
//		}
//		if(recommended == null || recommended.size() == 0)
//		{
//			recommendation = "Not enough information to make a recommendation on activities you should avoid.";
//		}
//
//		return recommendation;
//	}
//	@GetMapping("recommendations/dinner")
//	public String largeDinnerRecommendation(HttpServletResponse res){
//		String recommendation = "";
//		try {
//			if(crunch.largeDinnerRecommendation() == null) {
//				recommendation = "Not enough information";
//			}else if(crunch.largeDinnerRecommendation()) {
//				recommendation = "From the information you have submitted, it looks like you sleep better with a larger dinner";
//			}else {
//				recommendation = "From the information you have submitted, it looks like you sleep better with a smaller dinner";
//				
//			}
//			
//			
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		
//		return recommendation;
//	}
//	@GetMapping("recommendations/nap")
//	public String napRecommendation(HttpServletResponse res){
//		String recommendation = "";
//		try {
//			if(crunch.napRecommendation() == null) {
//				recommendation = "Not enough information";
//			}else if(crunch.napRecommendation()) {
//				recommendation = "From the information you have submitted, it looks like naps don't affect your sleep quality";
//			}else {
//				recommendation = "From the information you have submitted, it looks like you should avoid naps";
//				
//			}
//			
//			
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		
//		return recommendation;
//	}
//	@GetMapping("recommendations/workout")
//	public String workoutRecommendation(HttpServletResponse res){
//		Workout recommendation = null;
//		try {
//			if(crunch.workoutTimeRecomendation() == null) {
//				res.setStatus(204);
//			}else {
//				recommendation = crunch.workoutTimeRecomendation();
//				res.setStatus(200);
//			}
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		
//		String workoutTime = "You sleep best when you workout around " + recommendation.getTimeOfDay() + ".";
//		return workoutTime;
//	}
//	@GetMapping("recommendations/sleepStart")
//	public String sleepStartRecommendation(HttpServletResponse res){
//		String recommendation = null;
//		try {
//			recommendation = crunch.timeStartRecommendation();
//			if(recommendation == null) {
//				res.setStatus(404);
//			}
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		return recommendation;
//	}
//	@GetMapping("recommendations/sleepEnd")
//	public String sleepEndRecommendation(HttpServletResponse res){
//		String recommendation = null;
//		try {
//			recommendation = crunch.timeStopRecommendation();
//			if(recommendation == null) {
//				res.setStatus(404);
//			}
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		return recommendation;
//	}
//	@GetMapping("recommendations/duration")
//	public String durationRecommendation(HttpServletResponse res){
//		String recommendation = null;
//		try {
//			recommendation = crunch.timeElapsedRecommendation();
//			if(recommendation == null) {
//				res.setStatus(404);
//			}
//		}catch(Exception e) {
//			res.setStatus(500);
//			e.printStackTrace();
//		}
//		return recommendation;
//	}
	
	
	
}

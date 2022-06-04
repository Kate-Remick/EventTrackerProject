package com.skilldistillery.sleeptracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.sleeptracker.entities.Workout;
import com.skilldistillery.sleeptracker.services.WorkoutService;

@RestController
@RequestMapping("api")
public class WorkoutController {

	@Autowired
	private WorkoutService ws;
	
	@GetMapping("workouts")
	public List<Workout> showAll(HttpServletResponse res){
		List<Workout> wkts = null;
		try {
			wkts = ws.findAll();
			if(wkts == null || wkts.size() == 0) {
				res.setStatus(500);
			}else {
				res.setStatus(200);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(500);
		}
		return wkts;
	}
	
}

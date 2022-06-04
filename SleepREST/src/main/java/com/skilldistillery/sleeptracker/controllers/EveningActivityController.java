package com.skilldistillery.sleeptracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.sleeptracker.entities.EveningActivity;
import com.skilldistillery.sleeptracker.services.EveningActivityService;

@RestController
@RequestMapping("api")
public class EveningActivityController {
	
	@Autowired
	EveningActivityService east;
	
	@GetMapping("activities")
	public List<EveningActivity> showAll(HttpServletResponse res){
		List<EveningActivity> ea = null;
		try {
			ea = east.findAll();
			if(ea == null) {
				res.setStatus(500);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ea = null;
			res.setStatus(500);
		}
		return ea;
	}
	@PostMapping("activities")
	public EveningActivity showAll(HttpServletResponse res, @RequestBody EveningActivity ea){
		try {
			ea = east.createActivity(ea);
			if(ea == null) {
				res.setStatus(404);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ea = null;
			res.setStatus(400);
		}
		return ea;
	}
	

}

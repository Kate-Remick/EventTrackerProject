package com.skilldistillery.sleeptracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.sleeptracker.entities.SleepPeriod;
import com.skilldistillery.sleeptracker.services.SleepPeriodService;

@RestController
@RequestMapping("api")
public class SleepPeriodController {

	@Autowired
	private SleepPeriodService sps;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("sleep/{id}")
	public SleepPeriod findById(@PathVariable int id, HttpServletResponse res) {
		SleepPeriod sp = null;
		try {
			sp = sps.findById(id);
			if (sp == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return sp;

	}
	@GetMapping("sleep")
	public List<SleepPeriod> findAll(HttpServletResponse res) {
		List<SleepPeriod> allSleep = null;
		try {
			allSleep = sps.findAll();
			if (allSleep == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return allSleep;
		
	}
	@PostMapping("sleep")
	public SleepPeriod addSleep(@RequestBody SleepPeriod sp, HttpServletResponse res) {
		try {
			sp = sps.addSleep(sp);
			if (sp == null) {
				res.setStatus(204);
			} else {
				res.setStatus(201);
			}
		} catch (Exception e) {
			res.setStatus(400);
			sp = null;
			e.printStackTrace();
		}
		return sp;
		
	}
	@PutMapping("sleep/{id}")
	public SleepPeriod editSleep(@RequestBody SleepPeriod sp, HttpServletResponse res, @PathVariable int id) {
		try {
			sp = sps.editSleep(sp, id);
			if (sp == null) {
				res.setStatus(204);
			} else {
				res.setStatus(201);
			}
		} catch (Exception e) {
			res.setStatus(400);
			sp = null;
			e.printStackTrace();
		}
		return sp;
		
	}
	
	@DeleteMapping("sleep/{id}")
	public void removeSleep(@PathVariable int id, HttpServletResponse res) {
		try {
			if(sps.removeSleep(id)) {
				res.setStatus(204);
			}else {
				res.setStatus(404);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	@GetMapping("sleep/workout/{id}")
	public List<SleepPeriod> findByWorkoutTime(@PathVariable int id, HttpServletResponse res){
		List<SleepPeriod> sleep = null;
		try {
			sleep = sps.findByWorkoutTime(id);
			if(sleep == null || sleep.size() == 0) {
				res.setStatus(204);
			}else {
				res.setStatus(200);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return sleep;
	}
	@GetMapping("sleep/activity/{id}")
	public List<SleepPeriod> findByEveningActivity(@PathVariable int id, HttpServletResponse res){
		List<SleepPeriod> sleep = null;
		try {
			sleep = sps.findByEveningActivity(id);
			if(sleep == null || sleep.size() == 0) {
				res.setStatus(204);
			}else {
				res.setStatus(200);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return sleep;
	}
	@GetMapping("sleep/{low}/{high}")
	public List<SleepPeriod> findByQualityGroup(@PathVariable int low,@PathVariable int high,  HttpServletResponse res){
		List<SleepPeriod> sleep = null;
		try {
			sleep = sps.findByQualityGroup(low, high);
			if(sleep == null || sleep.size() == 0) {
				res.setStatus(204);
			}else {
				res.setStatus(200);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return sleep;
	}

}

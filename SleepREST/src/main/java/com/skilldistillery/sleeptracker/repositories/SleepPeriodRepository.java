package com.skilldistillery.sleeptracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.sleeptracker.entities.SleepPeriod;
import com.skilldistillery.sleeptracker.entities.Workout;

public interface SleepPeriodRepository extends JpaRepository<SleepPeriod, Integer>{

	List<SleepPeriod> findByWorkout_IdEquals(@Param("id") int id);
	
	List<SleepPeriod> findByEveningActivity_IdEquals(@Param("id") int id);
	
	@Query("SELECT sp FROM SleepPeriod sp WHERE sp.quality >= :low AND sp.quality <= :high")
	public List<SleepPeriod> findByQualityGroup(@Param("low") int low, @Param("high")int high);
	
}

package com.skilldistillery.sleeptracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.sleeptracker.entities.EveningActivity;

public interface EveningActivityRepository extends JpaRepository<EveningActivity, Integer>{

}

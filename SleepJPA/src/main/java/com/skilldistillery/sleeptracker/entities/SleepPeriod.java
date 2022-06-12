package com.skilldistillery.sleeptracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sleep_period")
public class SleepPeriod {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	private Double duration;
	
	@Column(name="had_alcohol")
	private boolean hadAlcohol;
	
	@Column(name="large_dinner")
	private boolean largeDinner;

	private boolean exercised;
	
	@Column(name="took_nap")
	private boolean tookNap;
	
	private Integer quality;
	
	
	@ManyToOne
	@JoinColumn(name="evening_activity_id")
	private EveningActivity eveningActivity;
	
	@ManyToOne
	@JoinColumn(name="workout_id")
	private Workout workout;
	
	public SleepPeriod() {
		
		
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SleepPeriod other = (SleepPeriod) obj;
		return id == other.id;
	}




	public SleepPeriod(LocalDateTime start, LocalDateTime end, boolean hadAlcohol, boolean largeDinner,
			boolean excercised, String eveningActivity, boolean tookNap, int quality) {
		super();
		this.start = start;
		this.end = end;
		this.hadAlcohol = hadAlcohol;
		this.largeDinner = largeDinner;
		this.exercised = excercised;
		this.tookNap = tookNap;
		this.quality = quality;
		
		double elapsed;
		double valueStart = (this.getStart().getHour() + (Math.round((this.getStart().getMinute()/60))*100)/100);
		double valueEnd = (this.getEnd().getHour() + (Math.round((this.getEnd().getMinute()/60))*100)/100);
		if(valueEnd > valueStart) {
			elapsed = valueEnd - valueStart;
		}else {
			elapsed = ((24 - valueStart) + valueEnd);
		}
		
		this.duration = elapsed;
	}








	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		double elapsed;
		double valueStart = (this.getStart().getHour() + (Math.round((this.getStart().getMinute()/60))*100)/100);
		double valueEnd = (this.getEnd().getHour() + (Math.round((this.getEnd().getMinute()/60))*100)/100);
		if(valueEnd > valueStart) {
			elapsed = valueEnd - valueStart;
		}else {
			elapsed = ((24 - valueStart) + valueEnd);
		}
		
		this.duration = elapsed;
		
		
	}

	public boolean isHadAlcohol() {
		return hadAlcohol;
	}

	public void setHadAlcohol(boolean hadAlcohol) {
		this.hadAlcohol = hadAlcohol;
	}

	public boolean isLargeDinner() {
		return largeDinner;
	}

	public void setLargeDinner(boolean largeDinner) {
		this.largeDinner = largeDinner;
	}

	public boolean isExercised() {
		return exercised;
	}

	public void setExercised(boolean excercised) {
		this.exercised = excercised;
	}


	public boolean isTookNap() {
		return tookNap;
	}

	public void setTookNap(boolean tookNap) {
		this.tookNap = tookNap;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}




	public EveningActivity getEveningActivity() {
		return eveningActivity;
	}




	public void setEveningActivity(EveningActivity eveningActivity) {
		this.eveningActivity = eveningActivity;
	}




	public Workout getWorkout() {
		return workout;
	}




	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	
	
	
}

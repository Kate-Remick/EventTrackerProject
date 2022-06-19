import { ActivityService } from './../../services/activity.service';
import { EveningActivity } from './../../models/evening-activity';
import { SleepPeriod } from './../../models/sleep-period';
import { SleepService } from './../../services/sleep.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { WorkoutService } from 'src/app/services/workout.service';
import { Workout } from 'src/app/models/workout';

@Component({
  selector: 'app-sleep-tracker',
  templateUrl: './sleep-tracker.component.html',
  styleUrls: ['./sleep-tracker.component.css']
})
export class SleepTrackerComponent implements OnInit {
  nights: SleepPeriod[] = [];
  activities: EveningActivity[] = [];
  workouts: Workout [] = []
  recommendations: string[] = [];
  newNight: SleepPeriod = new SleepPeriod();
  editNight: SleepPeriod = new SleepPeriod();
  newActivity: EveningActivity = new EveningActivity();
  toggleSleepForm: boolean = false;
  toggleActivityForm: boolean = false;
  toggleRecommendations: boolean = false;
  toggleShowData: boolean = false;
  editSelect: SleepPeriod | null = null;


//******************* Setup ******************* */
  constructor(private sleepServ: SleepService, private route:ActivatedRoute, private router: Router,
    private activeServ: ActivityService, private recServ: RecommendationService, private workoutServ: WorkoutService) { }

  ngOnInit(): void {
    this.reload();
    this.getEveningActivities();
    this.getRecommendations();
    this.getWorkouts();
  }
//************* Page Dynamics Methods ********//

selectEdit(selected: SleepPeriod): void{
  console.log("in select edit:" + selected)
  this.editSelect = selected;
}


//********** Data Access Methods ************//
reload(): void{
  this.sleepServ.index().subscribe({
    next: (nights) => {
      this.nights = nights;
    },
    error: (problem) => {
      console.error('HttpComponent.reload(): error loading todos:');
      console.error(problem);
    }
  });
}

getEveningActivities(): void{
  this.activeServ.index().subscribe({
    next: (activities) => {
      this.activities = activities;
    },
    error: (problem) => {
      console.error(' error ');
      console.error(problem);
    }
  });

}

getWorkouts(): void{
  this.workoutServ.index().subscribe({
    next: (workouts) => {
      this.workouts = workouts;
    },
    error: (problem) => {
      console.error('H: error');
      console.error(problem);
    }
  });
}
getRecommendations(): void{
  this.recServ.index().subscribe({
    next: (recommendations) => {
      this.recommendations = recommendations;
    },
    error: (problem) => {
      console.error(' error :');
      console.error(problem);
    }
  });
}

createNight(): void{
  console.log(this.newNight)
  this.sleepServ.create(this.newNight).subscribe({
    next: () => {
      this.newNight = new SleepPeriod();
      this.reload();
    },
    error: (problem) => {
      console.error('error :');
      console.error(problem);
    }
  });
}

updateNight(night: SleepPeriod): void{
  console.log(this.selectEdit)
  this.sleepServ.update(night).subscribe({
    next: () => {
      this.editSelect = null;
      this.reload();
    },
    error: (problem) => {
      console.error('error :');
      console.error(problem);
    }
  });
}
createActivity(): void{
  this.activeServ.create(this.newActivity).subscribe({
    next: () => {
      this.newActivity = new EveningActivity();
      this.reload();
      this.getEveningActivities();
    },
    error: (problem) => {
      console.error('error :');
      console.error(problem);
    }
  });
}

deleteNight(id: number): void{
  console.log("in delete: " + id)
  this.sleepServ.delete(id).subscribe({
    next: () => {
      this.reload();
    },
    error: (problem) => {
      console.error('error :');
      console.error(problem);
    }
  });
}
}


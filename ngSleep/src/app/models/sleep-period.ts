import { EveningActivity } from './evening-activity';
import { Workout } from './workout';
export class SleepPeriod {
  id: number;
  quality: number;
  duration: number;
  start: Date | null;
  end: Date | null;
  hadAlcohol: boolean;
  largeDinner: boolean;
  exercised: boolean;
  tookNap: boolean;
  eveningActivity: EveningActivity | null;
  workout: Workout | null;
  constructor(id: number = 0, quality: number = 0, duration: number = 0,
    hadAlcohol: boolean = false, largeDinner: boolean = false, exercised: boolean = false,
    tookNap: boolean = false, eveningActivity: EveningActivity |null = null, workout: Workout | null = null,
    end: Date | null = null, start: Date | null= null){
    this.id = id;
    this.quality = quality;
    this.duration = duration;
    this.hadAlcohol = hadAlcohol;
    this.largeDinner = largeDinner;
    this.exercised = exercised;
    this.tookNap = tookNap;
    this.eveningActivity = eveningActivity;
    this.workout = workout;
    this.start = start;
    this.end = end;

  }

}

package WorkoutGenerator.WorkoutGenerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class WorkoutController {

	//Request handler for the default URI
	
	@RequestMapping ("/getworkout")
	public Workout getWorkout(@RequestParam(value="get workout", defaultValue="None") String getworkout) {
		System.out.println("We have made it to Workout Controller");
		WorkoutHandler wrkHandler = WorkoutHandler.getInstance();
		Workout workout = wrkHandler.getWorkout();
		return workout;
	}
	
	@RequestMapping("/exercises")
	public Exercise exercise (@RequestParam(value="exercisename", defaultValue="None") String exercisename){
		Exercise exercise = new Exercise(exercisename, 0, 0, 0, exercisename, exercisename);
		return exercise;
		
	}

}

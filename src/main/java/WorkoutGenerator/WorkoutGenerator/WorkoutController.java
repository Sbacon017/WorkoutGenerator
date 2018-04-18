package WorkoutGenerator.WorkoutGenerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/")
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
	public String exerciseHomePage (@RequestParam(value="exercisename", defaultValue="None") String exercisename){
		return "exercisehome.html";
		
	}
	
	@RequestMapping(value = "/addNewExercise")
	public String addNewExercisePage() {
		return "redirect:/static/addNewExercise.html";
	}
	
	@RequestMapping("/exerciseAdded")
	public Exercise exerciseAdded(@RequestParam(value="name", defaultValue="None") String name,
			@RequestParam("weight") int weight,
			@RequestParam("sets") int sets,
			@RequestParam("reps") int reps,
			@RequestParam(value="notes", defaultValue="") String notes,
			@RequestParam(value="type", defaultValue="") String type) {
		
		ExerciseHandler exHandler = new ExerciseHandler();
		exHandler.addNewExercise(name, weight, sets, reps, notes, type);
		return new Exercise(name, weight, sets, reps, notes, type);
		
	}
	
	@RequestMapping("/exerciseUpdated")
	public String updateExercist(@RequestParam(value="name", defaultValue="None") String name,
			@RequestParam("weight") int weight,
			@RequestParam("sets") int sets,
			@RequestParam("reps") int reps,
			@RequestParam(value="notes", defaultValue="") String notes,
			@RequestParam(value="type", defaultValue="") String type) {
		ExerciseHandler exHandler = new ExerciseHandler();
		exHandler.updateExercise(name, weight, sets, reps, notes, type);
		return "Exercise updated.";
	}

}

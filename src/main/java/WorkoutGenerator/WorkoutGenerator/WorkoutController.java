package WorkoutGenerator.WorkoutGenerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class WorkoutController {
	
;

	//Request handler for the default URI
	@RequestMapping()
	public String goHome() {
		return "redirect:classpath/static/home.html";
	}
	
	@RequestMapping ("/getworkout")
	public Workout workout(@RequestParam(value="numReps", defaultValue="10") int numReps) {
		return;
	}
	
	@RequestMapping("/exercises")
	public Exercise exercise (@RequestParam(value="exercisename", defaultValue="None") String exercisename){
		Exercise exercise = new Exercise(exercisename);
		return exercise;
		
	}

}

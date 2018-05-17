package WorkoutGenerator.WorkoutGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import WorkoutGenerator.WorkoutGenerator.model.Workout;
import WorkoutGenerator.WorkoutGenerator.repository.ExerciseRepository;
import WorkoutGenerator.WorkoutGenerator.repository.WorkoutRepository;
import handlers.ExerciseHandler;
import handlers.WorkoutHandler;
import handlers.EmailHandler;

import javax.validation.Valid;
import java.util.List;

import java.io.*;

import org.json.*;
/*
 * ExerciseController is the RestController for the whole program,
 * and directs all incoming requests to their respective handlers.
 */
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
	public JavaMailSender sender;
	
	//Id of the current exercise, assigned when a single exercise is 
	// retrieved via get request.
	private long currentEx;
	
	//Get all exercises
	@GetMapping("/exercises")
	public List<Exercise> getAllExercises(){
		return ExerciseHandler.getAllExercises(exerciseRepository);
	}
	
	//Create a new exercise
	@PostMapping("/exercises")
	public Exercise createExercise(@Valid @RequestBody String exercise) {
		System.out.println(exercise);
		try {
		JSONObject obj = new JSONObject(exercise);
		Exercise newEx = new Exercise();
		newEx.setExercisePropsFromJSON(obj);
		return ExerciseHandler.createNewExercise(exerciseRepository, newEx);
		} catch (Exception e) {
			e.printStackTrace();
			return new Exercise();
		}
		
	}
	
	//Get a single exercise by id
	@GetMapping("exercises/id/{id}")
	public Exercise getExById(@PathVariable(value="id") Long exerciseId) {
		return ExerciseHandler.getExById(exerciseRepository, exerciseId);
	}
	
	//Get a single exercise by name
	@GetMapping("exercises/name/{name}")
	public Exercise getExByName(@PathVariable(value="name") String name) {
		Exercise ex = ExerciseHandler.getExByName(exerciseRepository, name);
		this.currentEx = ex.getId();
		return ex;
	}
	
	//Get all exercises by type
	@GetMapping("exercises/type/{type}")
	public List<Exercise> getAllExByType(@PathVariable(value="type") String type){
		return ExerciseHandler.getExByType(exerciseRepository, type);
	}
	
	// Update.
	@PutMapping("exercises")
	public Exercise updateExercise(@Valid @RequestBody String exercise) {
		try {
			JSONObject obj = new JSONObject(exercise);
			Exercise newEx = new Exercise();
			newEx.setExercisePropsFromJSON(obj);
			//WRONG THIS IS WRONG THAT WILL BE A NEW ID NOT THE OLD ONE!!
			return ExerciseHandler.updateExercise(exerciseRepository, newEx, this.currentEx);
		} catch (Exception e) {
			e.printStackTrace();
			return new Exercise();
		}
		
	}
	
	//Delete an exercise
	@DeleteMapping("/exercises/delete")
	public ResponseEntity<?> deleteExercise(){
		return ExerciseHandler.deleteExercise(exerciseRepository, this.currentEx);
	}
	
	// Get a random workout with default num exercises
	@GetMapping("/workout")
	public Workout getDefaultRandomWorkout() {
		return WorkoutHandler.getDefaultSizeWorkout(exerciseRepository, workoutRepository);
	}
	
	// Get a random workout for a specific number of exercises
	@GetMapping("/workout/number/{numEx}")
	public Workout getWorkoutWithSize(@PathVariable(value = "numEx") int numEx) {
		return WorkoutHandler.getWorkout(exerciseRepository,  workoutRepository,  numEx);
	}
	
	// Get a random workout for a specific type
	@GetMapping("/workout/type/{type}")
	public Workout getWorkoutWithType(@PathVariable(value = "type") String type) {
		return WorkoutHandler.getDefaultTypedWorkout(exerciseRepository,  workoutRepository, type);
	}
	
	// Get a random workout for a specific type and number of exercises
	@GetMapping("/workout/type/{type}/{numEx}")
	public Workout getWorkoutWithSizeAndType(@PathVariable(value = "type") String type, 
			@PathVariable(value = "numEx") int numEx) {
		return WorkoutHandler.getTypedWorkout(exerciseRepository, workoutRepository, numEx, type);
	}
	
	// Sends an email of the results section to the specified email
	@PostMapping("/email/{email}")
	public String sendEmail(@PathVariable(value = "email") String email, 
			@Valid @RequestBody String content) {
		System.out.println(email);
		System.out.println(content);
		EmailHandler emHandler = new EmailHandler();
		emHandler.sendMail(sender, email, content);
		return "Success!";
	}



}





















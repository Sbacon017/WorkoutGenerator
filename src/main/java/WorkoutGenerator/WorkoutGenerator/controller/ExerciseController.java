package WorkoutGenerator.WorkoutGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import WorkoutGenerator.WorkoutGenerator.model.Workout;
import WorkoutGenerator.WorkoutGenerator.repository.ExerciseRepository;
import WorkoutGenerator.WorkoutGenerator.repository.WorkoutRepository;
import handlers.ExerciseHandler;
import handlers.WorkoutHandler;

import javax.validation.Valid;
import java.util.List;

import java.io.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	//Get all exercises
	@GetMapping("/exercises")
	public List<Exercise> getAllExercises(){
		return ExerciseHandler.getAllExercises(exerciseRepository);
	}
	
	//Create a new exercise
	@PostMapping("/exercises")
	public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
		return ExerciseHandler.createNewExercise(exerciseRepository, exercise);
	}
	
	//Get a single exercise by id
	@GetMapping("exercises/id/{id}")
	public Exercise getExById(@PathVariable(value="id") Long exerciseId) {
		return ExerciseHandler.getExById(exerciseRepository, exerciseId);
	}
	
	//Get a single exercise by name
	@GetMapping("exercises/name/{name}")
	public Exercise getExByName(@PathVariable(value="name") String name) {
		return ExerciseHandler.getExByName(exerciseRepository, name);
	}
	
	//Get all exercises by type
	@GetMapping("exercises/type/{type}")
	public List<Exercise> getAllExByType(@PathVariable(value="type") String type){
		return ExerciseHandler.getExByType(exerciseRepository, type);
	}
	
	// Update.
	@PutMapping("exercises/{id}")
	public Exercise updateExercise(@PathVariable(value="id") Long exerciseId,
									@Valid @RequestBody Exercise exercise) {
		return ExerciseHandler.updateExercise(exerciseRepository, exercise, exerciseId);
		
	}
	
	//Delete an exercise
	@DeleteMapping("/exercises/{id}")
	public ResponseEntity<?> deleteExercise(@PathVariable(value="id") Long exerciseId){
		return ExerciseHandler.deleteExercise(exerciseRepository, exerciseId);
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
	


}





















package WorkoutGenerator.WorkoutGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import WorkoutGenerator.WorkoutGenerator.Exception.ResourceNotFoundException;
import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import WorkoutGenerator.WorkoutGenerator.model.Workout;
import WorkoutGenerator.WorkoutGenerator.repository.ExerciseRepository;
import handlers.TestHandler;
import handlers.WorkoutHandler;

import javax.validation.Valid;
import java.util.List;

import java.io.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	//Okay Start off with the simple stuff: CRUD.
	
	
	//Get all exercises
	@GetMapping("/exercises")
	public List<Exercise> getAllExercises(){
		//return exerciseRepository.findAll();
		return TestHandler.getAllExercises(exerciseRepository);
	}
	
	//Create a new exercise
	@PostMapping("/exercises")
	public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
		return exerciseRepository.save(exercise);
	}
	
	//Get a single exercise
	@GetMapping("exercises/{id}")
	public Exercise getExById(@PathVariable(value="id") Long exerciseId) {
		return exerciseRepository.findById(exerciseId)
				.orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exerciseId));
	}
	
	// Update: skip for right now. Too many data fields LOLCATZ omg I hate you.
	
	//Delete an exercise
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteExercise(@PathVariable(value="id") Long exerciseId){
		Exercise ex = exerciseRepository.findById(exerciseId)
				.orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exerciseId));
		exerciseRepository.delete(ex);
		return ResponseEntity.ok().build();
	}

}





















package handlers;

import WorkoutGenerator.WorkoutGenerator.Exception.ResourceNotFoundException;
import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import WorkoutGenerator.WorkoutGenerator.repository.ExerciseRepository;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.io.*;

public class ExerciseHandler {
	
	// Get all exercises
	public static List<Exercise> getAllExercises(ExerciseRepository exr){
		return exr.findAll();
	}
	
	// Get single exercise by id
	public static Exercise getExById(ExerciseRepository exr, long id) {
		return exr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));
	}
	
	//Get a single exercise by name
	public static Exercise getExByName(ExerciseRepository exr, String name) {
		return exr.findByName(name);
	}
	
	//Get all exercises by type
	public static List<Exercise> getExByType(ExerciseRepository exr, String type){
		return exr.findByType(type);
	}
	
	//Create new exercise
	public static Exercise createNewExercise(ExerciseRepository exr, Exercise exercise){
		return exr.save(exercise);
	}
	
	//Update existing exercise
	// coming back to this...
	public static Exercise updateExercise(ExerciseRepository exr, Exercise ex, long id) {
		Exercise exercise = exr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));
		exercise.setNotes(ex.getNotes());
		exercise.setReps(ex.getReps());
		exercise.setSets(ex.getSets());
		exercise.setWeight(ex.getWeight());
		return exr.save(exercise);
	}
	
	//Delete exercise
	public static ResponseEntity<?> deleteExercise(ExerciseRepository exr,  Long exId){
		Exercise ex = exr.findById(exId)
				.orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exId));
		exr.delete(ex);
		return ResponseEntity.ok().build();
	}

}

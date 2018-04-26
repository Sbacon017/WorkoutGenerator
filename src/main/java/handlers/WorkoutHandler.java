package handlers;

import WorkoutGenerator.WorkoutGenerator.Exception.ResourceNotFoundException;
import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import WorkoutGenerator.WorkoutGenerator.model.Workout;
import WorkoutGenerator.WorkoutGenerator.repository.ExerciseRepository;
import WorkoutGenerator.WorkoutGenerator.repository.WorkoutRepository;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.io.*;


public class WorkoutHandler {
	
	private static final int DEFAULT_EXERCISES = 5;
	
	//Get random workout with specified size and type
	public static Workout getWorkout(ExerciseRepository exr, WorkoutRepository wrkr, int numExercises) {
		Workout workout = new Workout();
		List<Integer> numSet = getNumSet(numExercises, exr.findAll().size());
		for (Integer num : numSet) {
			Exercise exercise = exr.findById(Integer.toUnsignedLong(num))
					.orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", num));
			workout.addExercise(exercise);
		}
		return wrkr.save(workout);
	}
	
	// Get random workout with a default size
	public static Workout getDefaultSizeWorkout(ExerciseRepository exr, WorkoutRepository wrkr) {
		return getWorkout(exr, wrkr, DEFAULT_EXERCISES);
	}
	
	// Get a random workout with a particular type and a particular size
	public static Workout getTypedWorkout(ExerciseRepository exr, WorkoutRepository wrkr, int numEx, String type) {
		Workout workout = new Workout();
		List<Exercise> exList = exr.findByType(type);
		if (numEx > exList.size()) {
			throw new IllegalArgumentException("Not enough exercises to satisfy list needs.");
		}
		Collections.shuffle(exList);
		for (int i = 0; i < numEx; i++) {
			workout.addExercise(exList.get(i));
		}
		return workout;
	}
	
	// Get a random workout with a particular type and the default num exercises
	public static Workout getDefaultTypedWorkout(ExerciseRepository exr, WorkoutRepository wrkr, String type) {
		return getTypedWorkout(exr, wrkr, DEFAULT_EXERCISES, type);
	}
	
	/* Helper method to generate a list of numbers of a particular size
	 * within a particular range
	 */
	private static List<Integer> getNumSet(int numNeeded, int numAvailable){
		if (numNeeded > numAvailable) {
			throw new IllegalArgumentException("Not enough exercises to satisfy list needs.");
		}
		List<Integer> numList = new ArrayList<Integer>();
		for (int i = 1; i <= numAvailable; i++) {
			numList.add(i);
		}
		Collections.shuffle(numList);
		return numList.subList(0, numNeeded);	
	}

}

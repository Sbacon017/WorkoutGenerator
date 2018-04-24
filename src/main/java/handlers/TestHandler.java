package handlers;

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

import javax.validation.Valid;
import java.util.List;

import java.io.*;

public class TestHandler {
	
	public static List<Exercise> getAllExercises(ExerciseRepository exr){
		return exr.findAll();
	}

}

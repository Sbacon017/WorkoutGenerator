package WorkoutGenerator.WorkoutGenerator;

import java.util.HashMap;

/*
 * An interface for various kinds of databases that could be used.
 * 
 * Currently, the only requirement is that there be a public method
 * that returns a HashMap of Exercise object-related data. 
 */

public interface WorkoutDatabaseInterface {
	
	public HashMap getExerciseData();
	

}

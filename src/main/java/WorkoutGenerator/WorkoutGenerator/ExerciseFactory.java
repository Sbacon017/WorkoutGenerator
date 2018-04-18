package WorkoutGenerator.WorkoutGenerator;

import java.util.HashMap;

/*
 * Factory class that generates Exercise Objects.
 * Contains an instance of WorkoutDBFacade.
 * 
 * 
 */

public class ExerciseFactory {
	
	//A private connection to the Workout Database
	private WorkoutDatabaseInterface wrkDBFace;
	
	//Constructs with an instance of the Derby database client
	ExerciseFactory(){
		this.wrkDBFace = new DerbyWorkoutDatabase();
	}
	
	
	/*
	 * Method: getExercise
	 * Input: Varies, method overloaded, none necessary
	 * Output: An Exercise object with data from the DB
	 * 
	 * Desc: Relies on the database interface to produce a HashMap of 
	 * data to Exercise object fields, which is then used to produce an exercise object. 
	 * 
	 * Overloaded to produce Exercises of particular field requirements
	 */
	public Exercise getExercise() {
		System.out.println("We have called getExercise in ExFactory.");
		
		HashMap exHash = wrkDBFace.getExerciseData();
		Exercise newEx = new Exercise((String)exHash.get("name"), (int)exHash.get("weight"), (int)exHash.get("sets"),
				(int)exHash.get("reps"), (String)exHash.get("notes"), (String)exHash.get("type"));
		return newEx;		
	}
	
	public Exercise createExercise(String name, int weight, int sets, int reps, String notes, String type) {
		return new Exercise(name, weight, sets, reps, notes, type);
	}

}

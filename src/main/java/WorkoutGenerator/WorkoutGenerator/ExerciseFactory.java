package WorkoutGenerator.WorkoutGenerator;

import java.util.HashMap;

/*
 * Factory class that generates Exercise Objects.
 * Contains an instance of WorkoutDBFacade
 */

public class ExerciseFactory {
	
	private WorkoutDatabaseInterface wrkDBFace;
	
	
	//Exercise(String name, int weight, int sets, int reps, String notes, String type){
	
	//Gets a random exercise from the Facade
	public Exercise getExercise() {
		HashMap exHash = wrkDBFace.getExerciseData();
		Exercise newEx = new Exercise((String)exHash.get("name"), (int)exHash.get("weight"), (int)exHash.get("sets"),
				(int)exHash.get("reps"), (String)exHash.get("notes"), (String)exHash.get("type"));
		return newEx;
		
		
	}

}

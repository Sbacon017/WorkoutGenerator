package WorkoutGenerator.WorkoutGenerator;

/*Class that handles the CRUD for individual Exercises.
 * Contains an instance of both ExerciseFactory Object and the
 * WorkoutDatabaseInterface object. 
 */

public class ExerciseHandler {
	
	ExerciseFactory exFact;
	WorkoutDatabaseInterface wrkoutDB;
	
	ExerciseHandler(){
		this.exFact = new ExerciseFactory();
		this.wrkoutDB = new DerbyWorkoutDatabase();
	}
	
	public void addNewExercise(String name, int weight, int sets, 
			int reps, String notes, String type) {
		Exercise newEx = exFact.createExercise(name, weight, sets, reps, notes, type);
		wrkoutDB.addExToDatabase(newEx);
	}
	
	public void updateExercise(String name, int weight, int sets, 
			int reps, String notes, String type){
		Exercise updatedEx = exFact.createExercise(name,  weight,  sets,  reps,  notes,  type);
		wrkoutDB.updateExerciseData(updatedEx);
	}
	
	
	

}

package WorkoutGenerator.WorkoutGenerator;

/*
 * Generator class for Workouts. Contains an instance
 * of the ExerciseFactory Class, and an instance of the 
 * WorkoutDBFacade Class.
 */

public class WorkoutFactory {
	
	private ExerciseFactory ExFact;
	private WorkoutDatabaseInterface WrkDBFace;
	
	private static final int DEFAULT_EXERCISES = 5;
	
	WorkoutFactory(){
		this.ExFact = new ExerciseFactory();
	}
	
	
	/*
	 * Method: getSimpleRandomWorkout
	 * Input: None
	 * Output: Workout with constant DEFAULT_EXERCISES number of 
	 * 		exercises. 
	 * 
	 * Desc: Simple version of a Workout generator, calls on the
	 * 		class instance of a ExerciseFactory to generate the
	 * 		DEFAULT_EXERCISES number of Exercises.
	 */
	public Workout getSimpleRandomWorkout() {
		
		System.out.println("We have called getSimpleRandomWorkout in Workout Factory.");
		
		//Instantiate a new Array of Exercises, sized by the constant
		Exercise[] exArray = new Exercise[DEFAULT_EXERCISES];
		
		//Iterates through to create each exercise
		for (int i = 0; i < exArray.length; i++) {
			System.out.println("Calling ExFact.getExercise!");
			exArray[i] = ExFact.getExercise();
			
		}
		
		//Returns a Workout object composed of the new exercises. 
		return new Workout(exArray);
		
	}
	

}

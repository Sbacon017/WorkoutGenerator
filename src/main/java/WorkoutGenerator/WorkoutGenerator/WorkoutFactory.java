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
	
	public Workout getSimpleRandom() {
		Exercise[] exArray = new Exercise[DEFAULT_EXERCISES];
		for (int i = 0; i < exArray.length; i++) {
			exArray[i] = ExFact.getExercise();
			
		}
		return new Workout(exArray);
		
	}
	

}

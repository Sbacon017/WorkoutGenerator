package WorkoutGenerator.WorkoutGenerator;


/*
 * A superclass of exercise groups, primarily an 
 * array of Exercise objects and methods for acting upon them. 
 */
public class Workout {
	
	//The ExerciseArray
	private Exercise[] exercises;
	
	//Constructor
	Workout(Exercise[] exercises){
		this.exercises = exercises;
	}
	
	//Get array method
	public Exercise[] getExercises() {
		return this.exercises;
	}
	
	/*
	 * Method: printExercises
	 * Input: The class Exercise Array
	 * Output: Long String of data formatted by tabs. 
	 */
	public String printExercises() {
		String formattedExercises = "Name\tWeight\tSets\tReps\tType\tNotes\n";
		for (int i = 0; i < this.exercises.length; i++) {
			formattedExercises += (this.exercises[i].getName() + "\t" + this.exercises[i].getWeight() +
					"\t" + this.exercises[i].getSets() + "\t" + this.exercises[i].getReps() + "\t"
					+ this.exercises[i].getType() + "\t" + this.exercises[i].getNotes() + "\n");
		}
		System.out.println("Formatted exercise: \n" +formattedExercises);
		return formattedExercises;
	}

}

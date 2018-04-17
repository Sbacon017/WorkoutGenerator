package WorkoutGenerator.WorkoutGenerator;


/*
 * A superclass of exercise groups that contains 
 */
public class Workout {
	
	private Exercise[] exercises;
	
	
	Workout(Exercise[] exercises){
		this.exercises = exercises;
	}
	
	public Exercise[] getExercises() {
		return this.exercises;
	}
	
	public String printExercises() {
		String formattedExercises = "";
		for (int i = 0; i < this.exercises.length; i++) {
			formattedExercises += (this.exercises[i].getName() + "\t" + this.exercises[i].getWeight() +
					"\t" + this.exercises[i].getSets() + "\t" + this.exercises[i].getReps() + "\t"
					+ this.exercises[i].getType() + "\t" + this.exercises[i].getNotes() + "\n");
		}
		return formattedExercises;
	}

}

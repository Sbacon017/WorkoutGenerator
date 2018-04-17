package WorkoutGenerator.WorkoutGenerator;


/*
 * Data object class that is the cornerstone of this whole
 * project. Maniputlated and grouped into Workouts. 
 */
public class Exercise  {
	
	//Class data
	private String name;
	private int weight;
	private int sets;
	private int reps;
	private String notes;
	private String type;
	
	//Constructor.
	Exercise(String name, int weight, int sets, int reps, String notes, String type){
		this.name = name;
		this.weight = weight;
		this.sets = sets;
		this.reps = reps;
		this.notes = notes;
		this.type = type;
	}
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getSets() {
		return this.sets;
	}
	
	public int getReps() {
		return this.reps;
	}
	
	public String getNotes() {
		return this.notes;
	}
	
	public String getType() {
		return this.type;
	}

}

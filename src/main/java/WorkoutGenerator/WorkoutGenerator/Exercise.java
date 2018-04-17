package WorkoutGenerator.WorkoutGenerator;

public class Exercise  {
	
	//Class data
	private String name;
	private int weight;
	private int sets;
	private int reps;
	private String notes;
	private String type;
	
	
	Exercise(String name, int weight, int sets, int reps, String notes, String type){
		this.name = name;
		this.weight = weight;
		this.sets = sets;
		this.reps = reps;
		this.notes = notes;
		this.type = type;
	}
	
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

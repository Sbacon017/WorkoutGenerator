package WorkoutGenerator.WorkoutGenerator;


/*Class that handles the creation of new workouts. Contains an
 * instance of the Exercise Handler Class and the WorkoutFactoryClass. 
 * This class is a singleton: there should only be one
 * in the program at a time. 
 */

public class WorkoutHandler {
	
	//Compose from our other objects.
	private WorkoutFactory wrkFact = null;
	
	//Make this bad boy a Singleton
	private static WorkoutHandler instance = null;
	
	public static WorkoutHandler getInstance() {
		if (instance == null) {
			instance = new WorkoutHandler();
		}
		return instance;
	}
	
	//Constructor
	WorkoutHandler(){
		this.wrkFact = new WorkoutFactory();
	}
	
	/*Fun stuff. 
	 * Method: getWorkout()
	 * 
	 * Input: Preferences of workout (type, name, etc), or none
	 * 
	 * Output: Workout Object composed of Exercise Objects
	 * 
	 * Desc: Idea is to craft an overloaded method that spits
	 * back a WorkoutObject. Relies heavily on the WorkoutFactory
	 * 
	 * for now, just doing one for Simple Rando, one for type
	 */
	
	//Most basic: no options
	public Workout getWorkout() {
		return wrkFact.getSimpleRandom();
	}
	
	//Type of workout
//	public Workout getWorkout(String type) {
//		return WorkoutFactory.getTypedWorkout(type);
//	}
	
	

}

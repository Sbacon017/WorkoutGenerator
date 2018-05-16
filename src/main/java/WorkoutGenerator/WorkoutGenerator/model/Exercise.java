package WorkoutGenerator.WorkoutGenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import org.json.*;

/*
 * Data object class that is the cornerstone of this whole
 * project. Manipulated and grouped into Workouts. 
 */
@Entity
@Table(name = "exercises")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createdAt", "updatedAt"}, 
				allowGetters = true)
public class Exercise implements Serializable {
	
	//Class data
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exercise_id")
	private Long exerciseId;
	
	@NotBlank
	@Column(unique = true)
	private String name;
	
	private int weight;
	private int sets;
	private int reps;
	private String notes;
	private String type;
	
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;
	

	@ManyToMany(mappedBy = "exercises")
	private Set<Workout> workouts = new HashSet<>();

	
	//Constructors.
	public Exercise() {
		
	}
	
	public Exercise(String name, int weight, int sets, int reps, String notes, String type){
		this.name = name;
		this.weight = weight;
		this.sets = sets;
		this.reps = reps;
		this.notes = notes;
		this.type = type;
	}
	
	public void setExercisePropsFromJSON(JSONObject jobject) {
		try {
		setName(jobject.getString("name"));
		setWeight(jobject.getInt("weight"));
		setSets(jobject.getInt("sets"));
		setReps(jobject.getInt("reps"));
		setNotes(jobject.getString("notes"));
		setType(jobject.getString("type"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Getters
	public Long getId() {
		return exerciseId;
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
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setSets(int sets) {
		this.sets = sets;
	}
	
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void addWorkout(Workout workout) {
		this.workouts.add(workout);
	}

}

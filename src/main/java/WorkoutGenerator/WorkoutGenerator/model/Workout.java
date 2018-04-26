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
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
 * A superclass of exercise groups, primarily an 
 * array of Exercise objects and methods for acting upon them. 
 */
@Entity
@Table(name = "workouts")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createdAt", "updatedAt"}, 
				allowGetters = true)
public class Workout implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workout_id")
	private Long workoutId;
	
	@Column(name = "name", nullable = false, 
			columnDefinition = "varchar(255) default 'Workout'")
	private String name = "Workout";
	
	@Column(nullable = false, updatable = false, columnDefinition = "Date default '2018-4-25 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	
	@Column(nullable = false, columnDefinition = "Date default '2018-4-25 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;
	
	private String type;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "workout_exercise", 
				joinColumns = { @JoinColumn(name = "workout_id") }, 
					inverseJoinColumns = { @JoinColumn(name = "exercise_id") } )
	Set<Exercise> exercises = new HashSet<>();

	public Workout() {
		
	}
	
	//Getters and setters
	public Long getId() {
		return workoutId;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Set<Exercise> getExercises(){
		return exercises;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setUpdated(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	public void addExercise(Exercise exercise) {
		this.exercises.add(exercise);
	}

}



















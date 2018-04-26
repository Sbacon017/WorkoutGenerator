package WorkoutGenerator.WorkoutGenerator.repository;

import WorkoutGenerator.WorkoutGenerator.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	
	Exercise findByName(String name);
	
	List<Exercise> findByType(String type);

}

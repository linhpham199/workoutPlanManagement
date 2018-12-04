package fi.haagahelia.project.workout.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExerciseRepository extends CrudRepository<Exercise, Long>{
	List<Exercise> findByTitle(@Param("title") String title);
}

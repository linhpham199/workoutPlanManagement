package fi.haagahelia.project.workout.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlanRepository extends CrudRepository<Plan, Long> {
	List<Plan> findByName(@Param("name") String name);
}

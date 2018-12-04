package fi.haagahelia.project.workout.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BodyPartRepository extends CrudRepository<BodyPart, Long>{
	List<BodyPart> findByPart(@Param("name") String name);
}

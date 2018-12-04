package fi.haagahelia.project.workout.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SessionRepository extends CrudRepository<Session, Long> {
	List<Session> findByDate(@Param("date") Date date);
}

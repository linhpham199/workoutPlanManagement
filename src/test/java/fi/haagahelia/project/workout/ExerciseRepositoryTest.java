package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.BodyPart;
import fi.haagahelia.project.workout.domain.BodyPartRepository;
import fi.haagahelia.project.workout.domain.Exercise;
import fi.haagahelia.project.workout.domain.ExerciseRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExerciseRepositoryTest {
	@Autowired
	ExerciseRepository erepository;
	@Autowired
	BodyPartRepository bprepository;
	
	@Test
	public void addNewExercise() {
		Exercise exercise = new Exercise("Plank", "#", new BodyPart("Whole"), null);
		erepository.save(exercise);
		assertThat(exercise.getId()).isNotNull();
	}
	
	@Test
	public void deleteExercise() {
		Exercise exercise = new Exercise("Plank", "#", new BodyPart("Whole"), null);
		erepository.save(exercise);
		
		Long id = exercise.getId();
		erepository.deleteById(id);
		assertThat(erepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchExercise() {
		Exercise exercise = new Exercise("Plank", "#", bprepository.findByPart("Core").get(0), null);
		erepository.save(exercise);
		
		List<Exercise> newExercise = erepository.findByTitle(exercise.getTitle());
		assertThat(newExercise).contains(exercise);
	}
}

package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.BodyPartRepository;
import fi.haagahelia.project.workout.domain.ExerciseRepository;
import fi.haagahelia.project.workout.domain.PersonalTrainerRepository;
import fi.haagahelia.project.workout.domain.PlanRepository;
import fi.haagahelia.project.workout.domain.SessionRepository;
import fi.haagahelia.project.workout.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutApplicationTests {
	
	@Autowired
	private BodyPartRepository bprepository;
	
	@Autowired
	private ExerciseRepository erepository;
	
	@Autowired
	private PersonalTrainerRepository ptrepository;
	
	@Autowired
	private PlanRepository prepository;
	
	@Autowired
	private SessionRepository srepository;
	
	@Autowired
	private UserRepository urepository;

	@Test
	public void contextLoads() {
		assertThat(bprepository).isNotNull();
		assertThat(erepository).isNotNull();
		assertThat(ptrepository).isNotNull();
		assertThat(prepository).isNotNull();
		assertThat(srepository).isNotNull();
		assertThat(urepository).isNotNull();
	}

}

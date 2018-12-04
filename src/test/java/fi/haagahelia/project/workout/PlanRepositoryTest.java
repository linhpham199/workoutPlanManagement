package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.Exercise;
import fi.haagahelia.project.workout.domain.ExerciseRepository;
import fi.haagahelia.project.workout.domain.Plan;
import fi.haagahelia.project.workout.domain.PlanRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanRepositoryTest {
	@Autowired
	PlanRepository prepository;
	@Autowired
	ExerciseRepository erepository;
	
	@Test
	public void addNewPlan() {
		Set<Exercise> setE = new HashSet<Exercise>();
		Plan plan = new Plan("Monday", setE);
		Plan plan_2 = new Plan();
		prepository.save(plan);
		prepository.save(plan_2);
		assertThat(plan.getId()).isNotNull();
		assertThat(plan_2.getId()).isNotNull();
	}
	
	@Test
	public void deletePlan() {
		Set<Exercise> setE = new HashSet<Exercise>();
		Plan plan = new Plan("Monday", setE);
		prepository.save(plan);
		
		Long id = plan.getId();
		prepository.deleteById(id);
		assertThat(prepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchPlan() {
		Set<Exercise> setE = new HashSet<Exercise>();
		Plan plan = new Plan("Monday", setE);
		prepository.save(plan);
		
		List<Plan> newPlan = prepository.findByName(plan.getName());
		assertThat(newPlan).contains(plan);
	}
}

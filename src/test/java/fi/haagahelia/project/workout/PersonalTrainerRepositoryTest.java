package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.PersonalTrainer;
import fi.haagahelia.project.workout.domain.PersonalTrainerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonalTrainerRepositoryTest {
	@Autowired
	PersonalTrainerRepository ptrepository;
	
	@Test
	public void addNewTrainer() {
		PersonalTrainer pt = new PersonalTrainer("Tiia Seppo");
		ptrepository.save(pt);
		assertThat(pt.getId()).isNotNull();
	}
	
	@Test
	public void deleteTrainer() {
		PersonalTrainer pt = new PersonalTrainer("Tiia Seppo");
		ptrepository.save(pt);
		
		Long id = pt.getId();
		ptrepository.deleteById(id);
		assertThat(ptrepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchTrainer() {
		PersonalTrainer pt = new PersonalTrainer("Tiia Seppo");
		ptrepository.save(pt);
		
		PersonalTrainer newPt = ptrepository.findByName(pt.getName()).get(0);
		assertThat(newPt).isEqualTo(pt);
	}
}

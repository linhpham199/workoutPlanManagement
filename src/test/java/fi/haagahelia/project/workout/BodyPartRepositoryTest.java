package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.BodyPart;
import fi.haagahelia.project.workout.domain.BodyPartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BodyPartRepositoryTest {
	@Autowired
	private BodyPartRepository bprepository;
	
	@Test
	public void addNewBodyPart() {
		BodyPart bodypart = new BodyPart("Thigh");
		bprepository.save(bodypart);
		assertThat(bodypart.getPartId()).isNotNull();
	}
	
	@Test
	public void deleteBodyPart() {
		BodyPart bodypart = new BodyPart("Thigh");
		bprepository.save(bodypart);
		Long id = bodypart.getPartId();
		bprepository.deleteById(id);
		assertThat(bprepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchBodyPart() {
		BodyPart bodypart = new BodyPart("Thigh");
		bprepository.save(bodypart);
		
		BodyPart newbp = bprepository.findByPart(bodypart.getPart()).get(0);
		assertThat(newbp).isEqualTo(bodypart);	
	}
}

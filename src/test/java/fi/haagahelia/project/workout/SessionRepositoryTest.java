package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.PersonalTrainer;
import fi.haagahelia.project.workout.domain.PersonalTrainerRepository;
import fi.haagahelia.project.workout.domain.Plan;
import fi.haagahelia.project.workout.domain.PlanRepository;
import fi.haagahelia.project.workout.domain.Session;
import fi.haagahelia.project.workout.domain.SessionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SessionRepositoryTest {
	@Autowired
	SessionRepository srepository;
	@Autowired 
	PersonalTrainerRepository ptrepository;
	@Autowired
	PlanRepository prepository;
	
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	
	@Test
	public void addNewSession() {
		Session session = null;
		try {
			session = new Session(ft.parse("2018-12-11"), new PersonalTrainer("Amy B"), new Plan("Sunday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		srepository.save(session);
		assertThat(session.getId()).isNotNull();
	}
	
	@Test
	public void deleteSession() {
		Session session = null;
		try {
			session = new Session(ft.parse("2018-12-11"), new PersonalTrainer("Amy B"), new Plan("Sunday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		srepository.save(session);
		
		Long id = session.getId();
		srepository.deleteById(id);
		assertThat(srepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchSession() {
		Session session = null;
		try {
			session = new Session(ft.parse("2018-12-11"), ptrepository.findByName("Johnny Johnson").get(0), prepository.findByName("Plan A").get(0));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		srepository.save(session);
		
		List<Session> newSession = srepository.findByDate(session.getDate());
		assertThat(newSession).contains(session);
	}
}

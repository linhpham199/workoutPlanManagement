package fi.haagahelia.project.workout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.project.workout.domain.User;
import fi.haagahelia.project.workout.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void createNewUser() {
		User user = new User("user_test", "userpassowrd", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		User user = new User("user_test2", "userpassword", "USER");
		urepository.save(user);
		Long id = user.getId();
		urepository.deleteById(id);
		assertThat(urepository.findById(id)).isEmpty();
	}
	
	@Test
	public void searchUser() {
		User user = new User("user_test3", "userpassword", "USER");
		urepository.save(user);
		
		User username = urepository.findByUsername(user.getUsername());
		assertThat(user).isEqualTo(username);
	}
}

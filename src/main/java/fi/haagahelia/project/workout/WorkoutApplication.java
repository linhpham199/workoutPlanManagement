package fi.haagahelia.project.workout;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.project.workout.domain.BodyPart;
import fi.haagahelia.project.workout.domain.BodyPartRepository;
import fi.haagahelia.project.workout.domain.Exercise;
import fi.haagahelia.project.workout.domain.ExerciseRepository;
import fi.haagahelia.project.workout.domain.PersonalTrainer;
import fi.haagahelia.project.workout.domain.PersonalTrainerRepository;
import fi.haagahelia.project.workout.domain.Plan;
import fi.haagahelia.project.workout.domain.PlanRepository;
import fi.haagahelia.project.workout.domain.Session;
import fi.haagahelia.project.workout.domain.SessionRepository;
import fi.haagahelia.project.workout.domain.User;
import fi.haagahelia.project.workout.domain.UserRepository;

@SpringBootApplication
public class WorkoutApplication {
	private static final Logger log = LoggerFactory.getLogger(WorkoutApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkoutApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner workoutDemo(ExerciseRepository erepository, 
										 BodyPartRepository brepository, 
										 PlanRepository prepository, 
										 PersonalTrainerRepository ptrepository, 
										 SessionRepository srepository,
										 UserRepository urepository) {
		return (args) -> {
			log.info("Save couple of work out body parts");
			brepository.save(new BodyPart("Glutes"));
			brepository.save(new BodyPart("Leg"));
			brepository.save(new BodyPart("Chest"));
			brepository.save(new BodyPart("Arms and shoulders"));
			brepository.save(new BodyPart("Core"));
			brepository.save(new BodyPart("Back"));
			
			log.info("Save couple of work out exercises");
			erepository.save(new Exercise("Chest Press", "https://www.alive.com/fitness/resistance-for-your-health/#ChestPress", brepository.findByPart("Chest").get(0), null));
			erepository.save(new Exercise("Wall Angels", "https://www.alive.com/fitness/easy-office-exercises/#WallAngels", brepository.findByPart("Chest").get(0), null));
			erepository.save(new Exercise("Lunge Changes", "https://www.alive.com/fitness/why-not-try-plyometrics/#Lunge", brepository.findByPart("Leg").get(0), null));
			erepository.save(new Exercise("Cartoon Walking Lunges", "https://www.alive.com/fitness/run-the-right-way/#Cartoon", brepository.findByPart("Leg").get(0), null));
			erepository.save(new Exercise("Squat", "https://www.alive.com/fitness/resistance-for-your-health/#Squat", brepository.findByPart("Glutes").get(0), null));
			erepository.save(new Exercise("Bent Arm Dumbbell Lateral Raise", "https://www.alive.com/fitness/high-five-the-sleeveless-season/#LatRaise", brepository.findByPart("Arms and shoulders").get(0), null));
			erepository.save(new Exercise("Butterfly Crunch", "https://www.alive.com/fitness/core-power/#Butterfly", brepository.findByPart("Core").get(0), null));
			erepository.save(new Exercise("Superheroes", "https://www.alive.com/fitness/no-equipment-exercises/#Superheroes", brepository.findByPart("Back").get(0), null));
			erepository.save(new Exercise("Plank Kickouts", "https://www.alive.com/fitness/hard-core-training/#Kickouts", brepository.findByPart("Core").get(0), null));
			
			log.info("Create some exercise set");
			Set<Exercise> setA = new HashSet<Exercise>();
			Set<Exercise> setB = new HashSet<Exercise>();
			
			setA.add(erepository.findByTitle("Chest Press").get(0));
			setA.add(erepository.findByTitle("Lunge Changes").get(0));
			setA.add(erepository.findByTitle("Squat").get(0));
			setA.add(erepository.findByTitle("Superheroes").get(0));
			
			setB.add(erepository.findByTitle("Wall Angels").get(0));
			setB.add(erepository.findByTitle("Cartoon Walking Lunges").get(0));
			setB.add(erepository.findByTitle("Squat").get(0));
			setB.add(erepository.findByTitle("Bent Arm Dumbbell Lateral Raise").get(0));
			setB.add(erepository.findByTitle("Superheroes").get(0));

			
			log.info("Save couple of work out plans");
			prepository.save(new Plan("Plan A", setA));
			prepository.save(new Plan("Plan B", setB));
			prepository.save(new Plan("Plan C", setA));
			
			log.info("Save couple of personal trainer");
			ptrepository.save(new PersonalTrainer("Johnny Johnson"));
			ptrepository.save(new PersonalTrainer("Micheal Adams"));
			ptrepository.save(new PersonalTrainer("Julia Smith"));
			ptrepository.save(new PersonalTrainer("Ashley Johhanson"));
			ptrepository.save(new PersonalTrainer("Bradley Manley"));
			
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			log.info("Save couple of work out sessions");
			srepository.save(new Session(ft.parse("2018-12-11"), ptrepository.findByName("Johnny Johnson").get(0), prepository.findByName("Plan A").get(0)));
			srepository.save(new Session(ft.parse("2018-12-12"), ptrepository.findByName("Bradley Manley").get(0), prepository.findByName("Plan B").get(0)));
			srepository.save(new Session(ft.parse("2018-12-13"), ptrepository.findByName("Johnny Johnson").get(0), prepository.findByName("Plan C").get(0)));
			srepository.save(new Session(ft.parse("2018-12-14"), ptrepository.findByName("Ashley Johhanson").get(0), prepository.findByName("Plan C").get(0)));
			srepository.save(new Session(ft.parse("2018-12-14"), ptrepository.findByName("Micheal Adams").get(0), prepository.findByName("Plan A").get(0)));
			
			log.info("Save 2 users");
			User user1 = new User("user", "$2a$04$ga4FrSPyZ337wxPSW3euxO0AnLbO3BBoZcwvJ/bk8oU3trQjeLzxe", "USER"); //upassword
			User user2 = new User("admin", "$2a$04$7N9Un4iGso8ri3C71gIMYusRxBOfHDKfon8nHOruYxbuSy6YJT.yu", "ADMIN"); //apassword
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all exercises");
			for (Exercise exercise : erepository.findAll()) {
				log.info(exercise.toString());
			}
			
			log.info("fetch all plans");
			for (Plan plan: prepository.findAll()) {
				log.info(plan.toString());
			}
			
			log.info("fetch all sessions");
			for (Session session: srepository.findAll()) {
				log.info(session.toString());
			}
			
			log.info("fetch all users");
			for (User user: urepository.findAll()) {
				log.info(user.toString());
			}
		};
	}
}

package fi.haagahelia.project.workout.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.project.workout.domain.BodyPartRepository;
import fi.haagahelia.project.workout.domain.Exercise;
import fi.haagahelia.project.workout.domain.ExerciseRepository;
import fi.haagahelia.project.workout.domain.Plan;
import fi.haagahelia.project.workout.domain.PlanRepository;

@Controller
public class ExercisesController {
	@Autowired
	ExerciseRepository erepository;
	@Autowired
	BodyPartRepository brepository;
	@Autowired
	PlanRepository prepository;
	
	//Login
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	//Show all exercises
	@RequestMapping(value="/exercise_list", method=RequestMethod.GET)
	public String exerciselist(Model model){
		model.addAttribute("exercises", erepository.findAll());
		return "exercises_list";
	}
	
	//Add more exercises
	@RequestMapping(value = "/add")
	public String addExercise(Model model){
		model.addAttribute("exercise", new Exercise());
		System.out.println("EEEE: " +  new Exercise());
		model.addAttribute("bodyparts", brepository.findAll());
		return "add_exercise";
	}
	
	//Save new exercises
	@RequestMapping(value = "/save_e", method = RequestMethod.POST)
	public String saveExercise(Exercise exercise) {
		System.out.print(exercise);
		erepository.save(exercise);
		return "redirect:exercise_list";
	}
	
	//Delete an exercise by its id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteExercise(@PathVariable("id") Long eId, Model model) {
		for (Plan plan : prepository.findAll()) {
			 Collection<Exercise> exercises_set = plan.getExercises();
			 for (Exercise exercise : exercises_set) {
				 if (eId == exercise.getId()) {
					 return "delete_exercise_error";
				 }
			 }
		}
		System.out.println(eId);
		erepository.deleteById(eId);
		return "redirect:../exercise_list";
	}

	//Edit an exercise by its id
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editExercise(@PathVariable("id") Long eId, Model model) {
		erepository  
			.findById(eId)
			.ifPresent(exercise -> model.addAttribute("exercise", exercise));
		model.addAttribute("bodyparts", brepository.findAll());
		return "edit_exercise";
	}
	
}

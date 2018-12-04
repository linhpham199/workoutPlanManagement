package fi.haagahelia.project.workout.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.project.workout.domain.Exercise;
import fi.haagahelia.project.workout.domain.ExerciseRepository;
import fi.haagahelia.project.workout.domain.Plan;
import fi.haagahelia.project.workout.domain.PlanRepository;

@Controller
public class PlanController {
	@Autowired
	PlanRepository prepository;
	@Autowired
	ExerciseRepository erepository;
	
	//Show plans with exercises
	@RequestMapping(value="/plan_list", method=RequestMethod.GET)
	public String planlist(Model model){
		model.addAttribute("plans", prepository.findAll());
		return "plan_list";
	}
	
	//Add more plans
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add_p")
	public String addPlan(Model model){
		model.addAttribute("plan", new Plan());
		model.addAttribute("exercises", erepository.findAll());
		return "add_plan";
	}
	
	//Save new plan
	@RequestMapping(value = "/add_p" , method = RequestMethod.POST)
	public String addPlanSave(
			@ModelAttribute("plan") Plan plan,
			@RequestParam(value = "exercises", required = false) Long[] exerciseIds,
			BindingResult bindingResult, Model model) {
		if (exerciseIds != null) {
			Iterable<Exercise> newExercises = erepository.findAllById(Arrays.asList(exerciseIds));
			Set<Exercise> newExercisesSet = new HashSet();
			for (Exercise e : newExercises) {
				newExercisesSet.add(e);
			}
			plan.setExercises(newExercisesSet);
			prepository.save(plan);
		}
		return "redirect:plan_list";
	}
		
	//Delete a training plan by its id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete_p/{id}", method = RequestMethod.GET)
	public String deletePlan(@PathVariable("id") Long pId, Model model) {
		prepository.deleteById(pId);
		return "redirect:../plan_list";
	}
	
}
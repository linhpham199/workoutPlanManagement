package fi.haagahelia.project.workout.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.project.workout.domain.PersonalTrainerRepository;
import fi.haagahelia.project.workout.domain.PlanRepository;
import fi.haagahelia.project.workout.domain.Session;
import fi.haagahelia.project.workout.domain.SessionRepository;

@Controller
public class SessionController {
	@Autowired
	SessionRepository srepository;
	@Autowired
	PersonalTrainerRepository ptrepository;
	@Autowired
	PlanRepository prepository;
	
	//Show all training sessions 
	@RequestMapping(value="/session_list", method=RequestMethod.GET)
	public String sessions(Model model){
		model.addAttribute("sessions", srepository.findAll());
		return "session";
	}
		
	//Book a new session
	@RequestMapping(value="/add_s")
	public String addSession(Model model){
		model.addAttribute("session", new Session());
		model.addAttribute("pts", ptrepository.findAll());
		model.addAttribute("plans", prepository.findAll());
		return "add_session";
	}
	
	//Save new session
	@RequestMapping(value = "/save_s", method = RequestMethod.POST)
	public String saveSession(Session session) {
		System.out.println(session);
		srepository.save(session);
		return "redirect:session_list";
	}
	
	//Delete an session by its id
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete_s/{id}", method = RequestMethod.GET)
	public String deleteSession(@PathVariable("id") Long sId, Model model) {
		System.out.print(sId);
		srepository.deleteById(sId);
		return "redirect:../session_list";
	}
}

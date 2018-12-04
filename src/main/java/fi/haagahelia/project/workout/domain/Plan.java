package fi.haagahelia.project.workout.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "exerciseplan", joinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"))
	private Collection<Exercise> exercises;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
	private List<Session> sessions;
	
	public Plan() {}
	
	public Plan(String name) {
		this.name = name;
	}

	public Plan(String name, Set<Exercise> exercises) {
		super();
		this.name = name;
		this.exercises = exercises;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Collection<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Collection<Exercise> exercises) {
		this.exercises = exercises;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		String result = "Plan [id=" + id + ", name=" + name + ", ";
		if (exercises != null) {
            for (Exercise exercise : exercises) {
                result += " exercises= " + exercise.getTitle() + ",";
//            	System.out.print(exercise.getTitle());
            }
        }

		return  result;
	};
	
	
}

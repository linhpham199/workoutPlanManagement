package fi.haagahelia.project.workout.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BodyPart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long partId;
	private String part;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bodypart")
	private List<Exercise> exercises;
	
	public BodyPart() {};

	public BodyPart(String part) {
		super();
		this.part = part;
	}
	
	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "BodyPart [partId=" + partId + ", part=" + part + "]";
	}
	
}

package fi.haagahelia.project.workout.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exercise {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String href;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="partId")
	private BodyPart bodypart;
	
	@ManyToMany(mappedBy = "exercises")
	private Set<Plan> plans;
	
	public Exercise() {};
	
	public Exercise(String title) {
		this.title = title;
	};
	
	public Exercise(String title, String href, BodyPart part, Set<Plan> plans) {
		super();
		this.title = title;
		this.href = href;
		this.bodypart = part;
		this.plans = plans;
	}

	public BodyPart getPart() {
		return bodypart;
	}

	public void setPart(BodyPart part) {
		this.bodypart = part;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	
	@Column(nullable = true)
	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

	@Override
	public String toString() {
		return "Exercise [id=" + id + ", title=" + title + ", href=" + href + ", part=" + bodypart + "]";
	}
	
}

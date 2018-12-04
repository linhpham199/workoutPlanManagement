package fi.haagahelia.project.workout.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Session {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ptid")
	private PersonalTrainer pt;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="planid")
	private Plan plan;
	
	public Session() {}

	public Session(Date date, PersonalTrainer pt, Plan plan) {
		super();
		this.date = date;
		this.pt = pt;
		this.plan = plan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PersonalTrainer getPt() {
		return pt;
	}

	public void setPt(PersonalTrainer pt) {
		this.pt = pt;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", date=" + date + ", pt=" + pt + ", plan=" + plan + "]";
	};
	
	
	
}

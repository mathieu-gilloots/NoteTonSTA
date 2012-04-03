package com.supinfo.notetonsta.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Interventions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//nullable=false, car tout les champs doivent être renseignés.
	
	@Id @GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String subject;
	@Column(nullable=false)
	private Date fromPeriod;
	@Column(nullable=false)
	private Date toPeriod;
	@Column(nullable=false)
	private String description;
	
	@ManyToOne
	@JoinColumn
	private Campus campus;
	
	@ManyToOne
	@JoinColumn
	private Speakers speaker;
	
	@OneToMany(mappedBy="intervention")
	private List<Marks> marks;
	

	public Interventions(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(Date fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public Date getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(Date toPeriod) {
		this.toPeriod = toPeriod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public Speakers getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speakers speaker) {
		this.speaker = speaker;
	}

	public List<Marks> getMarks() {
		return marks;
	}

	public void setMarks(List<Marks> marks) {
		this.marks = marks;
	}
	
	
}

package com.supinfo.notetonsta.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Marks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Mise en non nullable des colonnes car toutes toutes les colonnes sauf comments doivent être renseignées
	
	@Id @GeneratedValue
	private Long id;
	@Column(nullable=false)
	private Long idBooster;
	@Column(nullable=false)
	private Float speakerQ1Mark;
	@Column(nullable=false)
	private Float speakerQ2Mark;
	@Column(nullable=false)
	private Float speakerQ3Mark;
	@Column(nullable=false)
	private Float slidesQ1Mark;
	@Column(nullable=false)
	private Float slidesQ2Mark;
	@Column(nullable=false)
	private Float slidesQ3Mark;
	private String comments;
	
	@ManyToOne
	@JoinColumn
	private Interventions intervention;
	
	public Marks(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdBooster() {
		return idBooster;
	}

	public void setIdBooster(Long idBooster) {
		this.idBooster = idBooster;
	}

	public Float getSpeakerQ1Mark() {
		return speakerQ1Mark;
	}

	public void setSpeakerQ1Mark(Float speakerQ1Mark) {
		this.speakerQ1Mark = speakerQ1Mark;
	}

	public Float getSpeakerQ2Mark() {
		return speakerQ2Mark;
	}

	public void setSpeakerQ2Mark(Float speakerQ2Mark) {
		this.speakerQ2Mark = speakerQ2Mark;
	}

	public Float getSpeakerQ3Mark() {
		return speakerQ3Mark;
	}

	public void setSpeakerQ3Mark(Float speakerQ3Mark) {
		this.speakerQ3Mark = speakerQ3Mark;
	}

	public Float getSlidesQ1Mark() {
		return slidesQ1Mark;
	}

	public void setSlidesQ1Mark(Float slidesQ1Mark) {
		this.slidesQ1Mark = slidesQ1Mark;
	}

	public Float getSlidesQ2Mark() {
		return slidesQ2Mark;
	}

	public void setSlidesQ2Mark(Float slidesQ2Mark) {
		this.slidesQ2Mark = slidesQ2Mark;
	}

	public Float getSlidesQ3Mark() {
		return slidesQ3Mark;
	}

	public void setSlidesQ3Mark(Float slidesQ3Mark) {
		this.slidesQ3Mark = slidesQ3Mark;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Interventions getIntervention() {
		return intervention;
	}

	public void setIntervention(Interventions intervention) {
		this.intervention = intervention;
	}
	
	
}

package com.supinfo.notetonsta.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Campus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	//By Default The Attribute is fetched with EAGER but not the Collection (Foreign Key)
	//So we have to change FetchType to EAGER
	@OneToMany(mappedBy="campus", fetch=FetchType.EAGER)
	private List<Interventions> interventions;
	
	public Campus(){
	}

	public Campus(String name){
		this.name = name;
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
	
	public List<Interventions> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Interventions> interventions) {
		this.interventions = interventions;
	}
	
	
}

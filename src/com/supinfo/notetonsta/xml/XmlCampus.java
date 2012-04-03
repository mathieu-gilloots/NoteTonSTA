package com.supinfo.notetonsta.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.supinfo.notetonsta.entity.Interventions;

@XmlRootElement(name="campus")
public class XmlCampus implements Serializable{
	
	private Long id;
	private String name;

	
	public XmlCampus(){
		
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
	


}

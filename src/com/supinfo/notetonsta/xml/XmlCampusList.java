package com.supinfo.notetonsta.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="campuses")
public class XmlCampusList{
	
	private List<XmlCampus> campuses;
	
	public XmlCampusList(){}
	
	public XmlCampusList(List<XmlCampus> campuses) {
		this.campuses = campuses;
	}

	@XmlElement(name="campus")
	public List<XmlCampus> getCampuses(){
		return campuses;
	}

	public void setCampuses(List<XmlCampus> campuses) {
		this.campuses = campuses;
	}

	@XmlElement
	public int getCount() {
		return campuses.size();
	}


}

package com.supinfo.notetonsta.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.xml.XmlCampusList;
import com.supinfo.notetonsta.xml.converter.XmlCampusConverter;
import com.supinfo.notetonsta.xml.converter.XmlCampusListConverter;


@Path("/campuses")
public class CampusResource {

	private CampusDAO campusDAO;
	private XmlCampusConverter xmlCampusConverter;
	private XmlCampusListConverter xmlCampusListConverter;
	
	public CampusResource(){
		this.campusDAO = DAOFactory.getInstance().getCampusDao();
		this.xmlCampusConverter = new XmlCampusConverter();
		this.xmlCampusListConverter = new XmlCampusListConverter();
	}
	
	
	@GET
	public XmlCampusList getAllCampus() {
		return xmlCampusListConverter.convertToXml(campusDAO.getAllCampus());
	}
}

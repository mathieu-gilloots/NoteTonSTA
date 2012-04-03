package com.supinfo.notetonsta.xml.converter;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.xml.XmlCampus;
import com.supinfo.notetonsta.xml.XmlCampusList;

public class XmlCampusListConverter implements XmlConverter<XmlCampusList, List<Campus>> {

	private XmlCampusConverter converter = new XmlCampusConverter();
	
	@Override
	public XmlCampusList convertToXml(List<Campus> campuses) {
		List<XmlCampus> xmlCampuses = new ArrayList<XmlCampus>();

		for (Campus campus : campuses) {
			xmlCampuses.add(converter.convertToXml(campus));
		}

		return new XmlCampusList(xmlCampuses);
	}

	@Override
	public List<Campus> convertFromXml(XmlCampusList xmlCampuses) {
		
		List<Campus> campuses = new ArrayList<Campus>();

		for (XmlCampus xmlStudent : xmlCampuses.getCampuses()) {
		campuses.add(converter.convertFromXml(xmlStudent));
		}

		return campuses;
	}

}

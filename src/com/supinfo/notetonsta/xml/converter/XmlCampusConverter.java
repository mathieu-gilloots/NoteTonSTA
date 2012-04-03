package com.supinfo.notetonsta.xml.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.xml.XmlCampus;

public class XmlCampusConverter implements XmlConverter<XmlCampus, Campus> {

	private Mapper mapper = new DozerBeanMapper();
	
	@Override
	public XmlCampus convertToXml(Campus campus) {
		return mapper.map(campus, XmlCampus.class);
	}

	@Override
	public Campus convertFromXml(XmlCampus xmlObj) {
		return mapper.map(xmlObj, Campus.class);
	}

}

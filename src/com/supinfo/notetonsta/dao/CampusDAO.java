package com.supinfo.notetonsta.dao;

import java.util.List;

import com.supinfo.notetonsta.entity.Campus;

public interface CampusDAO {
	
	Campus addCampus(Campus campus);
	Campus getCampusById(Long CampusId);
	Campus getCampusByName(String name);
	List<Campus> getAllCampus();
	
}

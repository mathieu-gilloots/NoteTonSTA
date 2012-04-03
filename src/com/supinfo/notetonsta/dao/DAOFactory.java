package com.supinfo.notetonsta.dao;

import com.supinfo.notetonsta.dao.jpa.JpaCampusDAO;
import com.supinfo.notetonsta.dao.jpa.JpaInterventionsDAO;
import com.supinfo.notetonsta.dao.jpa.JpaMarksDAO;
import com.supinfo.notetonsta.dao.jpa.JpaSpeakersDAO;
import com.supinfo.notetonsta.util.PersistenceManager;




public final class DAOFactory {
	
	private static DAOFactory instance;
	
	private CampusDAO CampusDAO;
	private InterventionsDAO InterventionDAO;
	private MarksDAO MarksDAO;
	private SpeakersDAO SpeakersDAO;
	
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public CampusDAO getCampusDao() {
		if(CampusDAO == null) {
			CampusDAO = new JpaCampusDAO(PersistenceManager.getEntityManagerFactory());
		}
		return CampusDAO;
	}
	
	public InterventionsDAO getInterventionsDAO() {
		if(InterventionDAO == null) {
			InterventionDAO = new JpaInterventionsDAO(PersistenceManager.getEntityManagerFactory());
		}
		return InterventionDAO;
	}
	
	public MarksDAO getMarksDAO() {
		if(MarksDAO == null) {
			MarksDAO = new JpaMarksDAO(PersistenceManager.getEntityManagerFactory());
		}
		return MarksDAO;
	}
	
	public SpeakersDAO getSpeakersDAO() {
		if(SpeakersDAO == null) {
			SpeakersDAO = new JpaSpeakersDAO(PersistenceManager.getEntityManagerFactory());
		}
		return SpeakersDAO;
	}

}

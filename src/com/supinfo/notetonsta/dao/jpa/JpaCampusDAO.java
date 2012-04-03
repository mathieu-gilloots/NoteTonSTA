package com.supinfo.notetonsta.dao.jpa;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.entity.Campus;

public class JpaCampusDAO implements CampusDAO{

	private EntityManagerFactory emf;
	
	public JpaCampusDAO(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@Override
	public Campus addCampus(Campus campus) {
		Campus result = null;
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(campus);
			em.getTransaction().commit();
			result = campus;
		}
		finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return result;
	}
	

	@Override
	public Campus getCampusById(Long CampusId) {
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Campus.class, CampusId);
		}
		finally{
			em.close();
		}
	}
	
	@Override
	public Campus getCampusByName(String name) {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("select c from Campus as c Where c.name = :name")
					.setParameter("name", name);
			return (Campus) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		finally{
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Campus> getAllCampus() {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("select c from Campus as c");
			return query.getResultList();
		}
		finally{
			em.close();
		}
	}



}

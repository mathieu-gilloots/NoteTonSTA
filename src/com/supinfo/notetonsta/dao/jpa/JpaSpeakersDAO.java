package com.supinfo.notetonsta.dao.jpa;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.notetonsta.dao.SpeakersDAO;
import com.supinfo.notetonsta.entity.Speakers;

public class JpaSpeakersDAO implements SpeakersDAO {

	private EntityManagerFactory emf;
	
	public JpaSpeakersDAO(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@Override
	public Speakers addSpeakers(Speakers speaker) {
		Speakers result = null;
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(speaker);
			em.getTransaction().commit();
			result = speaker;
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
	public Speakers getSpeakersById(Long SpeakerId) {
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Speakers.class, SpeakerId);
		}
		finally{
			em.close();
		}
	}
	
	@Override
	public Speakers getSpeakerByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("select s from Speakers as s Where s.email = :email")
					.setParameter("email", email);
			return (Speakers) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		finally{
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Speakers> getAllSpeakers() {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("select s from Speakers as s");
			return query.getResultList();
		}
		finally{
			em.close();
		}
	}

	

	@Override
	public Speakers LoginSpeaker(String email, String password) {
		Speakers result = null;
		EntityManager em = emf.createEntityManager();
		try{
			Speakers speaker = getSpeakerByEmail(email);
			if(speaker!=null && speaker.getPassword().equals(SHAPassword(password))){
				result = speaker;
			}
		}
		catch(NoResultException nre) {
			return null;
		}
		finally{
			em.close();
		}
		return result;
	}
	
	//Method to encrypt Password to SHA-1
	@Override
	public String SHAPassword(String password) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA1" );
	        md.update(password.getBytes() );
	        result = new BigInteger( 1, md.digest() ).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

}

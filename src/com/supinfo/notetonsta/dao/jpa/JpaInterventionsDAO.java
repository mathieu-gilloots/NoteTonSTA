package com.supinfo.notetonsta.dao.jpa;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.entity.Interventions;


public class JpaInterventionsDAO implements InterventionsDAO {

	private EntityManagerFactory emf;
	
	public JpaInterventionsDAO(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@Override
	public Interventions addInterventions(Interventions newIntervention) {
		Interventions result = null;
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(newIntervention);
			em.getTransaction().commit();
			result = newIntervention;
			
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
	public Interventions updateIntervention(Interventions intervention) {
		Interventions result = null;
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(intervention);
			em.getTransaction().commit();
			result = intervention;
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
	public void removeIntervention(Interventions intervention) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.remove(em.merge(intervention));
			em.getTransaction().commit();
		}
		finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Override
	public void removeIntervention(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.createQuery("DELETE FROM Interventions AS i Where i.id = :InterventionId")
				.setParameter("InterventionId", InterventionId)
				.executeUpdate();
			em.getTransaction().commit();
		}
		finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
	}


	@Override
	public Interventions getInterventionById(Long id) {
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Interventions.class, id);
		}
		finally{
			em.close();
		}
	}
	
	@Override
	public Long getNumberEvaluation(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("SELECT COUNT(m) FROM Marks as m Where m.intervention.id = :InterventionId")
					.setParameter("InterventionId", InterventionId);
			return (Long) query.getSingleResult();
		}
		finally{
			em.close();
		}
	}
	
	@Override
	public Float getAVGSpeakerMarks(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			if(getNumberEvaluation(InterventionId)>0){
				Double ReqRes = null;
				Query query = em.createQuery("SELECT AVG((speakerQ1Mark+speakerQ2Mark+speakerQ3Mark)/3) FROM Marks as m Where m.intervention.id = :InterventionId")
						.setParameter("InterventionId", InterventionId);
				ReqRes = (Double) query.getSingleResult();
				return ReqRes.floatValue();
			}
			else{
				return (float) 0;
			}
		}
		finally{
			em.close();
		}
	}

	@Override
	public Float getAVGSlideMarks(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			if(getNumberEvaluation(InterventionId)>0){
				Double ReqRes = null;
				Query query = em.createQuery("SELECT AVG((slidesQ1Mark+slidesQ2Mark+slidesQ3Mark)/3) FROM Marks as m Where m.intervention.id = :InterventionId")
						.setParameter("InterventionId", InterventionId);
				ReqRes = (Double) query.getSingleResult();
				return ReqRes.floatValue();
			}
			else{
				return (float) 0;
			}
		}
		finally{
			em.close();
		}
	}

	@Override
	public Float getAVGAllMarks(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			if(getNumberEvaluation(InterventionId)>0){
				Double ReqRes = null;
				Query query = em.createQuery("SELECT AVG((speakerQ1Mark+speakerQ2Mark+speakerQ3Mark+slidesQ1Mark+slidesQ2Mark+slidesQ3Mark)/6) FROM Marks as m Where m.intervention.id = :InterventionId")
						.setParameter("InterventionId", InterventionId);
				ReqRes = (Double) query.getSingleResult();
				return ReqRes.floatValue();
			}
			else{
				return (float) 0;
			}
		}
		finally{
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Float> getAVGAllMarksCombined(Long InterventionId) {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createQuery("SELECT (speakerQ1Mark+speakerQ2Mark+speakerQ3Mark+slidesQ1Mark+slidesQ2Mark+slidesQ3Mark)/6 FROM Marks as m Where m.intervention.id = :InterventionId Group By id")
					.setParameter("InterventionId", InterventionId);
			return (List<Float>) query.getResultList();
		}
		finally{
			em.close();
		}
	}

	@Override
	public String getStringChart(Long id) {
		//Generate the String Chart
		List<Float> MarksList = this.getAVGAllMarksCombined(id);
		ArrayList<Integer> MarksRound = new ArrayList<>();
		int counter, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
		String ChartList;
		Integer round;
		try{
			
			for (Float marksTemp  : MarksList) {
				round = Math.round(marksTemp);
				MarksRound.add(round);
			}
			
			//Now, we've got the array with rounded value, so count all value 1 by 1.
			for (int i = 0; i < 6; i++) {
				counter = 0;
				for (Integer value : MarksRound) {
					if(value==i){
						counter++;
					}
				}
				
				switch (i) {
				case 1: count1 = counter; break;
				case 2: count2 = counter; break;
				case 3: count3 = counter; break;
				case 4: count4 = counter; break;
				case 5: count5 = counter; break;
				}
			}	
			
			//Now, we've got the number of each rounded mark, creation of ChartString
			ChartList = "['1', " + count1 + "]";
			ChartList = ChartList + ", ['2', " + count2 + "]";
			ChartList = ChartList + ", ['3', " + count3 + "]";
			ChartList = ChartList + ", ['4', " + count4 + "]";
			ChartList = ChartList + ", ['5', " + count5 + "]";
			
			//Return the Chart String
			return ChartList;
		}
		catch (NullPointerException e) {
			return null;
		}
	
	}

}

package com.supinfo.notetonsta.dao.jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.supinfo.notetonsta.dao.MarksDAO;
import com.supinfo.notetonsta.entity.Marks;

public class JpaMarksDAO implements MarksDAO {

	private EntityManagerFactory emf;
	
	public JpaMarksDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Marks addMark(Marks newMark) {
		Marks result = null;
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(newMark);
			em.getTransaction().commit();
			result = newMark;
		}
		finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return result;
	}
	

}

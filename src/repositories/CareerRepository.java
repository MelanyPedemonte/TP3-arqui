package repositories;

import javax.persistence.EntityManager;
import daos.Career;

public class CareerRepository {
	
	private EntityManager em;

	public CareerRepository(EntityManager em) {
		this.em = em;
	}
	
	public Career saveCareer(Career c) {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public Career getCareerById(int i) {
		return em.find(Career.class, i);
	}	
	
	
	
	
	
}

package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import daos.Career;
import dtos.CareerDTO;

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
	
	/**
	 *
	 * @return
	 */
	public List<CareerDTO> getAllCareers() {
		List<CareerDTO> careers;
		Query query = em.createQuery(
				"SELECT new dtos.CareerDTO(c.id, c.nameCareer) FROM Career c ORDER BY c.nameCareer ASC");
		careers = query.getResultList();
		return careers;
	}
}

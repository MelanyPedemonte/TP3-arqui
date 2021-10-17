package repositories;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.transform.Transformers;


import daos.Inscription;
import dtos.CareerDTO;
import dtos.CareerReportDTO;


public class InscriptionRep {
	
	private EntityManager em;

	public InscriptionRep(EntityManager em) {
		this.em = em;
	}
	
	public Inscription saveInscription(Inscription i) {
		try {
			em.getTransaction().begin();
			em.persist(i);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	public Inscription getInscriptionById(Long id) {
		return em.find(Inscription.class, id);
	}
	
	
	
	/**
	 * Se retorna una lista de carreras con su respectiva cantidad de inscriptos
	 *
	 * @return List<CareerDTO>
	 */
	
	@SuppressWarnings("unchecked")
	public List<CareerDTO> getInscriptionCareers() {
		List<CareerDTO> result = new ArrayList<CareerDTO>();
	 	Query query = em.createQuery("SELECT new dtos.CareerDTO(c.nameCareer, COUNT(i.student) AS students) FROM Career c, Inscription i WHERE c.id = i.career GROUP BY c.nameCareer ORDER BY students DESC");
	 	result = query.getResultList();
	 	return result;

	}
	


    /**
     *  Metodo que devuelve la cantidad de inscriptos y egresados por año de cada carrera.
     *  Tuvimos que hacer la consulta en SQL nativo por JPQL no permite sub-consultas entonces no la podiamos resolver de otra forma con una sola consulta.
     * 
     * @return  Una lista ordenada de Carreras por nombre y año.
     */
	
	@SuppressWarnings("unchecked")
	public List<CareerReportDTO> getCareerOrderByNameAndYear(){
		
		List<CareerReportDTO> result = new ArrayList<CareerReportDTO>();
		Query query = em.createNativeQuery("select c.nameCareer, egresados.year, egresados.contadorEgresados, inscriptos.contadorInscripcion from career c join "
				+ "(select id_career, YEAR(graduation) as year, COUNT(graduation) as contadorEgresados from inscription where graduation is not null GROUP by id_career, year)"
				+ " as egresados on egresados.id_career = c.id_career join (select id_career, YEAR(start_date) as year, COUNT(start_date) as contadorInscripcion from inscription GROUP by id_career, year)"
				+ " as inscriptos where inscriptos.id_career = c.id_career GROUP BY c.nameCareer, year order by c.nameCareer, year");
		
		List<Object[]> results = query.getResultList();
				
		for (Object[] r : results) {
			// Se puede acceder a los datos de esta forma por que sabemos que la consulta es fija y no va a variar.
			result.add(new CareerReportDTO((String)r[0],(Integer)r[1],(BigInteger)r[2],(BigInteger)r[3]));
		}

	 	return result;
	}
	
	
}

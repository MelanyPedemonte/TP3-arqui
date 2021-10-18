package repositories;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import daos.Career;
import daos.Inscription;
import daos.Student;
import dtos.RegistrationDTO;
import dtos.StudentCareerDTO;
import dtos.StudentDTO;

public class StudentRepository {
	
	private EntityManager em;

	public StudentRepository(EntityManager em) {
		this.em = em;
	}
	
	
	public Student saveStudent(Student s) {
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public StudentDTO getStudentByNumBook(Integer nb) {
		StudentDTO std = new StudentDTO (em.find(Student.class, nb));
		//System.out.println(em.find(Student.class,(Integer) nb));
		return std;
	}
	
	/** 
	 * @param student
	 * @param insc
	 * 
	 * utilizamos la funcion studentInscription del dao de estudiantes 
	 * para completar la inscripcion.
	 */
	public void studentInscription(Student student, Inscription insc) {

		try {
			Student s = em.find(Student.class, student.getNumBook());
			em.getTransaction().begin();
			s.studentInscription(insc);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Se retorna una lista de todos los estudiantes
	 * 
	 * @return List<StudentDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<StudentDTO> getStudents() {
		List<StudentDTO> students;
		Query query = em.createQuery("SELECT new dtos.StudentDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident)"
				+ " FROM Student s ORDER BY s.name ASC");
		students = query.getResultList();
		return students;
	}
	
	
	/**
	 * Se retorna una lista de estudiantes en base a su genero
	 * 
	 * @param gender
	 * @return List<StudentDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<StudentDTO> getStudentsByGender(String gender) {
		List<StudentDTO> students;
		Query query = em.createQuery("SELECT new dtos.StudentDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident) FROM Student s WHERE s.gender = :gender");
		query.setParameter("gender", gender);
		students = query.getResultList();
		return students;
	}

	
	
	/**
	 * Se retorna una lista de estudiantes en base a una carrera (id_carrera) y a una ciudad
	 * especifica, ambos se pasan por parametro
	 * 
	 * @param idCareer
	 * @param city
	 * @return List<StudentCareerDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<StudentCareerDTO> getStudentCareer(int idCareer, String city) {
		List<StudentCareerDTO> students = new ArrayList<StudentCareerDTO>();
		Query query = em.createQuery(
				"SELECT new dtos.StudentCareerDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident, c.nameCareer) FROM Student s, Career c, Inscription i WHERE s.numBook = i.student AND c.id = i.career AND c.id = :id AND s.cityResident = :city GROUP BY i.student");
		query.setParameter("id", idCareer);
		query.setParameter("city", city);
		students = query.getResultList();
		return students;
	}
	
	/**
	 * Se retorna una lista de todas las ciudades de residencias de los estudiantes
	 * 
	 * @return List<String>
	 */
	public List<String> getCities() {
		List<String> cities;
		Query query = em.createQuery("SELECT DISTINCT s.cityResident"
				+ " FROM Student s");
		cities = query.getResultList();
		return cities;
	}
	
	
	/**
	 * Se le registra la inscripcion de la carrera del estudiante
	 * 
	 * @param student
	 * @param inscription
	 * 
	 */
	public void enroll(RegistrationDTO r) {

		try {

			Student studentFound = em.find(Student.class, r.getNumBook());
			Career careerFound = em.find(Career.class, r.getId_career());
			
			Inscription inscription = new Inscription();
			inscription.setCareer(careerFound);
			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date signupDate = datetimeFormatter1.parse(r.getStartDate());
			Timestamp t1 = new Timestamp(signupDate.getTime());
			inscription.setStartDate(t1);

			em.getTransaction().begin();
			studentFound.addCareeres(inscription);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}

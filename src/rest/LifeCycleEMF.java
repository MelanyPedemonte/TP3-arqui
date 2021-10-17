package rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import repositories.CareerRepository;
import repositories.InscriptionRepository;
import repositories.StudentRepository;

/*
 * Clase que se utiliza para manejar el ciclo de vida de la aplicacion
 * Se implementa la Clase ServletContextListener
 */

@WebListener
public class LifeCycleEMF implements ServletContextListener {

	private static EntityManagerFactory emf;
	private static StudentRepository studentRepository;
	private static InscriptionRepository inscriptionRepository;
	private static CareerRepository careerRepository;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		emf = Persistence.createEntityManagerFactory("Registro");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		createEntityManager().close();
		emf.close();
	}
	
	/*
	 * Metodo para obtener el EntityManager del EM Factory
	 */
	private static EntityManager createEntityManager() {
		if(emf == null){
			throw new IllegalStateException("Context is not initialazed yet.");
		}
		return emf.createEntityManager();
	}
	
	/*
	 * Metodo que se utiliza para obtener el repository de estudiantes
	 */
	public static StudentRepository getStudentRepository() {
		
		studentRepository = new StudentRepository(createEntityManager());
		return studentRepository;
	}
	
	/*
	 * Metodo que se utiliza para obtener el repository de inscripciones
	 */
	public static InscriptionRepository getInscriptionRepository() {
		inscriptionRepository = new InscriptionRepository(createEntityManager());
		return inscriptionRepository;
	}
	
	/*
	 * Metodo que se utiliza para obtener el repository de carreras
	 */
	public static CareerRepository getCareerRepository() {
		careerRepository = new CareerRepository(createEntityManager());
		return careerRepository;
	}
}

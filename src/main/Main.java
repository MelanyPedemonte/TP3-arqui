package main;

import java.util.List;
import java.io.FileReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import daos.Career;
import daos.Inscription;
import daos.Student;

import dtos.StudentDTO;
import dtos.CareerDTO;
import dtos.CareerReportDTO;
import dtos.StudentCareerDTO;
import repositories.CareerRepository;
import repositories.InscriptionRepository;
import repositories.StudentRepository;

class Main {

	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();

		StudentRepository studentRepository = new StudentRepository(em);
		CareerRepository careerRepository = new CareerRepository(em);
		InscriptionRepository inscriptionRepository = new InscriptionRepository(em);

		
		try { 
			
			loadDataStudent(studentRepository); 
			loadDataCareer(careerRepository);
			loadDataInscription(inscriptionRepository, em);
			
		} catch (Exception e) {
			System.out.println(e); 
		}	

		emf.close();
	}

	public static void loadDataStudent(StudentRepository repository) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/folder/estudiantes.csv"));
		for (CSVRecord row : parser) {

			Student e = new Student(Integer.parseInt(row.get("numBook")), row.get("name"), row.get("lastname"),
					Integer.parseInt(row.get("age")), row.get("gender"), Integer.parseInt(row.get("numDoc")),
					row.get("cityResident"));
			repository.saveStudent(e);
		}
	}

	public static void loadDataCareer(CareerRepository repository) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/folder/carreras.csv"));
		for (CSVRecord row : parser) {

			Career c = new Career(row.get("nameCareer"), Integer.parseInt(row.get("duration")));
			repository.saveCareer(c);
		}
	}

	public static void loadDataInscription(InscriptionRepository repository, EntityManager em) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/folder/inscripciones.csv"));
		for (CSVRecord row : parser) {

			Student s = em.getReference(Student.class, Integer.parseInt(row.get("numBook")));
			Career c = em.getReference(Career.class, Integer.parseInt(row.get("id_career")));

			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date lFromDate1 = datetimeFormatter1.parse(row.get("startDate"));
			Timestamp startD = new Timestamp(lFromDate1.getTime());
			Timestamp grad = null;
			if (!row.get("graduation").equals("")) {

				SimpleDateFormat datetimeFormatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date lFromDate2 = datetimeFormatter2.parse(row.get("graduation"));
				grad = new Timestamp(lFromDate2.getTime());
			}

			Inscription i = new Inscription(s, c, startD, grad);
			repository.saveInscription(i);
		}
	}

}

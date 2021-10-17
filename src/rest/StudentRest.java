package rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import daos.Student;
import repositories.StudentRepository;
import dtos.RegistrationDTO;
import dtos.StudentCareerDTO;
import dtos.StudentDTO;

@Path("/student")
public class StudentRest {

	/*
	 * Servicio para recuperar todas los estudiantes. 
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentDTO> getStudents() {
		return LifeCycleEMF.getStudentRepository().getStudents();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStudent(Student student) {
		StudentRepository instance = LifeCycleEMF.getStudentRepository();
		instance.saveStudent(student);
		return Response.status(201).entity(student).build();
	}
	
	/*
	 * Servicio para recuperar un estudiante en base a su numero de libreta universitaria (id). 
	 */
	
	@GET
	@Path("/{numLibret}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDTO getStudent(@PathParam("numLibret") int numLibret) {
		return LifeCycleEMF.getStudentRepository().getStudentByNumLibret(numLibret).get(0);
	}
	
	/*
	 * Servicio para recuperar todos los estudiantes de cierto genero. 
	 */
	
	@GET
	@Path("gender/{gender}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentDTO> getStudent(@PathParam("gender") String gender) {
		return LifeCycleEMF.getStudentRepository().getStudentsByGender(gender);
	}
	
	/*
	 * Estructura de JSON valida:
	 * {
	 *   "startDate": "yyyy-MM-dd hh:mm:ss", (Timestamp formato ISO)
	 *   "idCareer": int,
	 *   "idStudent": int
	 * }
	 * 
	 * Servicio para matricular a un estudiante en una carrera. 
	 *
	 */
	@POST
	@Path("/inscription")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerStudent(RegistrationDTO r) {
		LifeCycleEMF.getStudentRepository().enroll(r);
		return "Su matriculacion fue exitosa";
	}
	

	
	/*
	 * Llamado del servicio rest y su funcion es 
	 * obtener los estudiantes en relacion a una carrera 
	 * y la ciudad de residencia.
	 * 
	 * */
	@GET
	@Path("/{career}/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentCareerDTO> getStudentsByCity(@PathParam("career") int career, @PathParam("city") String city){
		StudentRepository instance = LifeCycleEMF.getStudentRepository();
		return instance.getStudentCareer(career, city);
		
	}
	
}

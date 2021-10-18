package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dtos.CareerDTO;
import dtos.CareerReportDTO;
import dtos.StudentCareerDTO;
import dtos.StudentDTO;

@Path("/career")
public class CareerRest {

	/*
	 * Servicio que recupera las carreras con inscriptos y dicha cantidad. 
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CareerDTO> getCareers() {
		 return LifeCycleEMF.getInscriptionRepository().getInscriptionCareers();
	}
	
	/*
	 * Servicio para recuperar el reporte de las carreras por año, sus inscriptos y egresados. 
	 */
	
	@Path("/report")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CareerReportDTO> getReport(){
		return LifeCycleEMF.getInscriptionRepository().getCareerOrderByNameAndYear();
	}
	
	/*
	 * Servicio que recupera todas las carreras. 
	 */
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CareerDTO> getCareer() {
		return LifeCycleEMF.getCareerRepository().getAllCareers();
	}
}

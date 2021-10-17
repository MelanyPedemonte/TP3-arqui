package dtos;

public class CareerDTO {
	
	private Integer id;
	private String career;
	private Long inscriptions;
	
	
	public CareerDTO(String career, Long inscriptions) {
		this.career = career;
		this.inscriptions = inscriptions;
	}
	
	

	public CareerDTO(Integer id, String career) {
		this.id = id;
		this.career = career;
	}



	public String getCareer() {
		return career;
	}


	public void setCareer(String career) {
		this.career = career;
	}


	public Long getInscriptions() {
		return inscriptions;
	}


	public void setInscriptions(Long inscriptions) {
		this.inscriptions = inscriptions;
	}


	public Integer getId() {
		return id;
	}


	@Override
	public String toString() {
		return "CareerDTO [id=" + id + ", career=" + career + ", inscriptions=" + inscriptions + "]";
	}

}

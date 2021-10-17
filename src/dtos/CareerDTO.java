package dtos;

public class CareerDTO {
	
	private String career;
	private Long inscriptions;
	
	
	public CareerDTO(String career, Long inscriptions) {
		super();
		this.career = career;
		this.inscriptions = inscriptions;
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


	@Override
	public String toString() {
		return "Career [career=" + career + ", inscriptions=" + inscriptions + "]";
	}

}

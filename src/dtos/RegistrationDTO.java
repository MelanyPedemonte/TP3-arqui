package dtos;

public class RegistrationDTO {

	private String signUpDate;
	private int idCareer;
	private int idStudent;
	
	public RegistrationDTO() {
		super();
	}

	public RegistrationDTO(String signUpDate, int idCareer, int idStudent) {
		super();
		this.signUpDate = signUpDate;
		this.idCareer = idCareer;
		this.idStudent = idStudent;
	}
	
	public String getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	public int getIdCareer() {
		return idCareer;
	}
	public void setIdCareer(int idCareer) {
		this.idCareer = idCareer;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	@Override
	public String toString() {
		return "RegistrationDTO [signUpDate=" + signUpDate + ", idCareer=" + idCareer + ", idStudent=" + idStudent
				+ "]";
	}
}

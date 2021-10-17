package dtos;

import java.math.BigInteger;

public class CareerReportDTO {
	
	private String nameCareer;
	private Integer year;
	private BigInteger qEnrolled;
	private BigInteger qGraduates;
	
	
	public CareerReportDTO(String nameCareer, Integer year, BigInteger qEnrolled, BigInteger qGraduates) {
		super();
		this.nameCareer = nameCareer;
		this.year = year;
		this.qEnrolled = qEnrolled;
		this.qGraduates = qGraduates;
	}


	public String getNameCareer() {
		return nameCareer;
	}


	public void setNameCareer(String nameCareer) {
		this.nameCareer = nameCareer;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public BigInteger getqEnrolled() {
		return qEnrolled;
	}


	public void setqEnrolled(BigInteger qEnrolled) {
		this.qEnrolled = qEnrolled;
	}


	public BigInteger getqGraduates() {
		return qGraduates;
	}


	public void setqGraduates(BigInteger qGraduates) {
		this.qGraduates = qGraduates;
	}


	@Override
	public String toString() {
		return "CareerReportDTO [nameCareer=" + nameCareer + ", year=" + year + ", qEnrolled=" + qEnrolled
				+ ", qGraduates=" + qGraduates + "]";
	}
	
	

	
	

}

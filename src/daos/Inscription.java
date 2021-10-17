package daos;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import dtos.CareerReportDTO;




@Entity
public class Inscription {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="numBook", nullable = false)
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_career",nullable=false)
	private Career career;
	
	@Column(name="start_date",nullable=false)
	private Timestamp startDate;
	
	@Column(name="graduation")
	private Timestamp graduation;

	public Inscription() {
		super();
	}

	public Inscription(Student student, Career career, Timestamp startDate, Timestamp graduation) {
		super();
		this.student = student;
		this.career = career;
		this.startDate = startDate;
		this.graduation = graduation;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getGraduation() {
		return graduation;
	}

	public void setGraduation(Timestamp graduation) {
		this.graduation = graduation;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Inscription [id=" + id + ", student=" + student + ", career=" + career + ", startDate=" + startDate
				+ ", graduation=" + graduation + "]";
	}	
	
	
}

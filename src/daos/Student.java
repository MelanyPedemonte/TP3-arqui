package daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
	@Column(nullable=false, name = "numBook")
	private int numBook;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@Column
	private int age;
	
	@Column
	private String gender;
	
	@Column
	private int numDoc;
	
	@Column
	private String cityResident;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
	private List<Inscription> careeres;
	
	public Student() {
		super();
	}
	
	public Student(int numBook, String name, String lastname, int age, String gender, int numDoc, String cityResident) {
		super();
		this.numBook = numBook;
		this.name = name;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.numDoc = numDoc;
		this.cityResident = cityResident;
		this.careeres = new ArrayList<Inscription>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(int numDoc) {
		this.numDoc = numDoc;
	}

	public String getCityResident() {
		return cityResident;
	}

	public void setCityResident(String cityResident) {
		this.cityResident = cityResident;
	}

	public List<Inscription> getCareeres() {
		return careeres;
	}

	public void addCareeres(Inscription career) {
		this.careeres.add(career);
	}

	public Integer getNumBook() {
		return numBook;
	}
	
	/**
	 * @param insc
	 * 
	 * agregamos la inscripcion a la lista de carreras en la que el estudiante
	 * esta inscripto
	 */
    public void studentInscription(Inscription insc) {
		this.addCareeres(insc);
		insc.setStudent(this);
	}

	@Override
	public String toString() {
		return "Estudent [numBook=" + numBook + ", name=" + name + ", lastname=" + lastname + ", age=" + age
				+ ", gender=" + gender + ", numDoc=" + numDoc + ", cityResident=" + cityResident + ", careeres="
				+ careeres + "]";
	}


	
	
	
	
	
	
}

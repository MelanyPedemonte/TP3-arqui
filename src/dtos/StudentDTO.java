package dtos;

import daos.Student;

/**
 * Se usa para mostrar los datos de los Estudiantes.
 *
 */
public class StudentDTO {
	
	private int numBook;
	private String name;
	private String lastname;
	private int age;
	private String gender;
	private int numDoc;
	private String cityResident;
	
	public StudentDTO(int numBook, String name, String lastname, int age, String gender, int numDoc, String cityResident) {
		super();
		this.numBook = numBook;
		this.name = name;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.numDoc = numDoc;
		this.cityResident = cityResident;
	}
	
	public StudentDTO(Student std) {
		super();
		this.numBook = (Integer) std.getNumBook();
		this.name = std.getName();
		this.lastname = std.getLastname();
		this.age = (Integer) std.getAge();
		this.gender = std.getGender();
		this.numDoc = (Integer) std.getNumDoc();
		this.cityResident = std.getCityResident();
	}

	public int getNumBook() {
		return numBook;
	}

	public void setNumBook(int numBook) {
		this.numBook = numBook;
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

	@Override
	public String toString() {
		return "Student [numBook=" + numBook + ", name=" + name + ", lastname=" + lastname + ", age=" + age
				+ ", gender=" + gender + ", numDoc=" + numDoc + ", cityResident=" + cityResident + "]";
	}

		
	
}

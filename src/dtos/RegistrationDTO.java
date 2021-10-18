package dtos;

public class RegistrationDTO {

	private String startDate;
	private int id_career;
	private int numBook;
	
	public RegistrationDTO() {
		super();
	}

	public RegistrationDTO(String startDate, int id_career, int numBook) {
		super();
		this.startDate = startDate;
		this.id_career = id_career;
		this.numBook = numBook;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getId_career() {
		return id_career;
	}

	public void setId_career(int id_career) {
		this.id_career = id_career;
	}

	public int getNumBook() {
		return numBook;
	}

	public void setNumBook(int numBook) {
		this.numBook = numBook;
	}

	@Override
	public String toString() {
		return "RegistrationDTO [startDate=" + startDate + ", id_career=" + id_career + ", numBook=" + numBook + "]";
	}
	
	
}

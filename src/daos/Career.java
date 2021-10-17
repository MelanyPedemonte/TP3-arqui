package daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Career {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_career")
	private int id;
	@Column
	private String nameCareer;
	@Column
	private int duration;
	
	public Career() {
		super();
	}
	public Career(String nameCareer, int duration) {
		super();
		this.nameCareer = nameCareer;
		this.duration = duration;
	}
	public String getNameCareer() {
		return nameCareer;
	}
	public void setNameCareer(String nameCareer) {
		this.nameCareer = nameCareer;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Career [id=" + id + ", nameCareer=" + nameCareer + ", duration=" + duration + "]";
	}

}
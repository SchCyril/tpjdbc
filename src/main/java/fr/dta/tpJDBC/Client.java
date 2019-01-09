package fr.dta.tpJDBC;

public class Client {
	private int id;
	private String lastname;
	private String firstname;
	public String gender;
	public Integer favbook;

	public Client(String lastname, String firstname, String gender, Integer favbook) {
		
		
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.favbook = favbook;
	}

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFavbook() {
		return favbook;
	}

	public void setFavbook(Integer favbook) {
		this.favbook = favbook;
	}




}

package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private long id;
	private String username;
	private String password;
	private int role;
	private String email;
	private String country;
	private int daily;
	private int weekly;
	private String kartica;
	/*
	 * Ako je nula znaci ne pripada ni jednoj kompaniji
	 * ako ima neki drugi broj znaci prodavac je ili op te kompanije
	 */
	private long companyId;
	/*
	 * nula znaci da nije obrisan,1-obrisan
	 */
	private int deleted;
	/*
	 *ocena po kojoj se izvlace useri 
	 */
	private int rate;
	
	public User() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getDaily() {
		return daily;
	}
	public void setDaily(int daily) {
		this.daily = daily;
	}
	public int getWeekly() {
		return weekly;
	}
	public void setWeekly(int weekly) {
		this.weekly = weekly;
	}
	public String getKartica() {
		return kartica;
	}
	public void setKartica(String kartica) {
		this.kartica = kartica;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public User(long id, String username, String password, int role, String email, String country, int daily,
			int weekly,long companyId,int deleted,int rate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.country = country;
		this.daily = daily;
		this.weekly = weekly;
		this.companyId = companyId;
		this.deleted = deleted;
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", email="
				+ email + ", country=" + country + ", daily=" + daily + ", weekly=" + weekly + ", kartica=" + kartica
				+ ", companyId=" + companyId + ", deleted=" + deleted + ", rate=" + rate + "]";
	}

}
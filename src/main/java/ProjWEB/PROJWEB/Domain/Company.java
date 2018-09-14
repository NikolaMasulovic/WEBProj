package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company {
	
	private long id;
	private String name;
	private String pib;
	private String loaction;
	private int fee;
	
	/*
	 * 0-classic
	 * 1-silver
	 * 2-gold
	 */
	private int partnerStatus;
	/*
	 * 0-NO,1-YES
	 */
	private int approved;
	private String email;
	
	public Company() {};
	
	public Company(long id,String name,String pib,String location,int fee,int partnerStatus,int approved,String email) {
		this.id = id;
		this.name = name;
		this.pib = pib;
		this.loaction = location;
		this.fee = fee;
		this.partnerStatus = partnerStatus;
		this.approved = approved;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getLoaction() {
		return loaction;
	}

	public void setLoaction(String loaction) {
		this.loaction = loaction;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getPartnerStatus() {
		return partnerStatus;
	}

	public void setPartnerStatus(int partnerStatus) {
		this.partnerStatus = partnerStatus;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", pib=" + pib + ", loaction=" + loaction + ", fee=" + fee
				+ ", partnerStatus=" + partnerStatus + ", approved=" + approved + ", email=" + email + "]";
	}

}

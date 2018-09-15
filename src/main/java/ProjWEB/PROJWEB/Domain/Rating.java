package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {

	private long id;
	private int ocena;
	private String date;
	private long userId;
	private long imageId;
	
	public Rating() {};
	
	public Rating(long id,int ocena,String date,long userId,long imageId) {
		this.id = id;
		this.ocena = ocena;
		this.date = date;
		this.userId = userId;
		this.imageId = imageId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", ocena=" + ocena + ", date=" + date + ", userId=" + userId + ", imageId="
				+ imageId + "]";
	}
}

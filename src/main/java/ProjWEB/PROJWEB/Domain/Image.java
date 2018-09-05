package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image {
	
	private long id;
	private int numOfCopiesSelled;
	private String datePublished;
	private int price;
	private String name;
	private String place;
	private String description;
	private String path;
	private long userId;
	private int approved;
	private String url;

	public Image() {}

	public Image(long id, int numOfCopiesSelled, String datePublished, int price, String name, String place,
			String description, long userId,String path,int approved) {
		super();
		this.id = id;
		this.numOfCopiesSelled = numOfCopiesSelled;
		this.datePublished = datePublished;
		this.price = price;
		this.name = name;
		this.place = place;
		this.description = description;
		this.userId = userId;
		this.path = path;
		this.approved = approved;
		this.url = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumOfCopiesSelled() {
		return numOfCopiesSelled;
	}

	public void setNumOfCopiesSelled(int numOfCopiesSelled) {
		this.numOfCopiesSelled = numOfCopiesSelled;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", numOfCopiesSelled=" + numOfCopiesSelled + ", datePublished=" + datePublished
				+ ", price=" + price + ", name=" + name + ", place=" + place + ", description=" + description
				+ ", path=" + path + ", userId=" + userId + ", approved=" + approved + ", url=" + url + "]";
	}


}

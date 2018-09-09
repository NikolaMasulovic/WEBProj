package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resolution {
	
	private long id;
	private long slikaId;
	private String resolution;
	private String path;
	private int price;
	private String base64;
	private int count;

    public Resolution() {}
	
	public Resolution(long id,long slikaId,String resolution,String path,int price,int count) {
		this.id = id;
		this.slikaId = slikaId;
		this.resolution = resolution;
		this.path = path;
		this.price = price;
		this.count = count;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSlikaId() {
		return slikaId;
	}
	public void setSlikaId(long slikaId) {
		this.slikaId = slikaId;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Resolution [id=" + id + ", slikaId=" + slikaId + ", resolution=" + resolution + ", path=" + path
				+ ", price=" + price + ", base64=" + base64 + ", count=" + count + "]";
	}
}

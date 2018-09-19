package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilterDto {
	private long firmaId;
	private String firmaNaziv;
	private String firmaNivo;
	private long userId;
	private String username;
	private double userOcena;
	private long photoId;
	private String photoName;
	private String uploadDate;
	private String path;
	private String url;
	private int selledCount;
	private double price;
	private double photoOcena;
	private int approved;
	private int deleted;
	private String location;
	private String opis;
	private long categoryId;
	private String categoryName;
	
	public FilterDto() {
		super();
	}

	public FilterDto(long firmaId, String firmaNaziv, String firmaNivo, long userId, String username,
			double userOcena, long photoId, String photoName, String uploadDate, String path, String base64,
			int selledCount, double price, double photoOcena, int approved, int deleted, String location, String opis) {
		super();
		this.firmaId = firmaId;
		this.firmaNaziv = firmaNaziv;
		this.firmaNivo = firmaNivo;
		this.userId = userId;
		this.username = username;
		this.userOcena = userOcena;
		this.photoId = photoId;
		this.photoName = photoName;
		this.uploadDate = uploadDate;
		this.path = path;
		this.url = base64;
		this.selledCount = selledCount;
		this.price = price;
		this.photoOcena = photoOcena;
		this.approved = approved;
		this.deleted = deleted;
		this.location = location;
		this.opis = opis;
	}

	

	public FilterDto(long firmaId, String firmaNaziv, String firmaNivo, long userId, String username,
			double userOcena, long photoId, String photoName, String uploadDate, String path, int selledCount,
			double price, double photoOcena, int approved, int deleted) {
		super();
		this.firmaId = firmaId;
		this.firmaNaziv = firmaNaziv;
		this.firmaNivo = firmaNivo;
		this.userId = userId;
		this.username = username;
		this.userOcena = userOcena;
		this.photoId = photoId;
		this.photoName = photoName;
		this.uploadDate = uploadDate;
		this.path = path;
		this.selledCount = selledCount;
		this.price = price;
		this.photoOcena = photoOcena;
		this.approved = approved;
		this.deleted = deleted;
	}
	public FilterDto(long firmaId, String firmaNaziv, String firmaNivo, long userId, String username,
			double userOcena, long photoId, String photoName, String uploadDate, String path, int selledCount,
			double price, double photoOcena, int approved, int deleted,long categoryId, String categoryName) {
		super();
		this.firmaId = firmaId;
		this.firmaNaziv = firmaNaziv;
		this.firmaNivo = firmaNivo;
		this.userId = userId;
		this.username = username;
		this.userOcena = userOcena;
		this.photoId = photoId;
		this.photoName = photoName;
		this.uploadDate = uploadDate;
		this.path = path;
		this.selledCount = selledCount;
		this.price = price;
		this.photoOcena = photoOcena;
		this.approved = approved;
		this.deleted = deleted;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public long getFirmaId() {
		return firmaId;
	}

	public void setFirmaId(long firmaId) {
		this.firmaId = firmaId;
	}

	public String getFirmaNaziv() {
		return firmaNaziv;
	}

	public void setFirmaNaziv(String firmaNaziv) {
		this.firmaNaziv = firmaNaziv;
	}

	public String getFirmaNivo() {
		return firmaNivo;
	}

	public void setFirmaNivo(String firmaNivo) {
		this.firmaNivo = firmaNivo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getUserOcena() {
		return userOcena;
	}

	public void setUserOcena(double userOcena) {
		this.userOcena = userOcena;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBase64() {
		return url;
	}

	public void setBase64(String base64) {
		this.url = base64;
	}

	public int getSelledCount() {
		return selledCount;
	}

	public void setSelledCount(int selledCount) {
		this.selledCount = selledCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPhotoOcena() {
		return photoOcena;
	}

	public void setPhotoOcena(double photoOcena) {
		this.photoOcena = photoOcena;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "SeptPhotoDto [firmaId=" + firmaId + ", firmaNaziv=" + firmaNaziv + ", firmaNivo=" + firmaNivo
				+ ", userId=" + userId + ", username=" + username + ", userOcena=" + userOcena + ", photoId=" + photoId
				+ ", photoName=" + photoName + ", uploadDate=" + uploadDate + ", path=" + path + ", base64=" + url
				+ ", selledCount=" + selledCount + ", price=" + price + ", photoOcena=" + photoOcena + ", approved="
				+ approved + ", deleted=" + deleted + ", location=" + location + ", opis=" + opis + "]";
	}
}

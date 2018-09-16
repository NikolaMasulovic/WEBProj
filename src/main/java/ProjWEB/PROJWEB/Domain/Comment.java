package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	
	private long id;
	private String comment;
	private String date;
	private long userId;
	private long slikaId;
	
	public Comment() {};
	
	public Comment(long id,String comment,String date,long userId,long slikaId) {
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.slikaId = slikaId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public long getSlikaId() {
		return slikaId;
	}

	public void setSlikaId(long slikaId) {
		this.slikaId = slikaId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", date=" + date + ", userId=" + userId + ", slikaId="
				+ slikaId + "]";
	}
}

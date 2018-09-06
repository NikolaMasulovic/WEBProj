package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.User;

@XmlRootElement
public class ImageUnapprovedDto {
	
	private User user;
	private List<Resolution> images;
	
	public ImageUnapprovedDto() {};
	
	public ImageUnapprovedDto(User user,List<Resolution> images) {
		this.user = user;
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Resolution> getImages() {
		return images;
	}

	public void setImages(List<Resolution> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ImageUnapprovedDto [user=" + user + ", images=" + images + "]";
	};
}

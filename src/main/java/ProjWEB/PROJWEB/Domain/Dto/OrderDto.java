package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.User;

public class OrderDto {

	private User user;
	private List<Image> images;
	
	public OrderDto() {};
	
	public OrderDto(User user,List<Image> images) {
		this.user = user;
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "OrderDto [user=" + user + ", images=" + images + "]";
	}
}

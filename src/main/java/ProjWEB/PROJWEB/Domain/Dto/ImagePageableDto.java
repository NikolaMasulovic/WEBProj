package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ProjWEB.PROJWEB.Domain.Image;

@XmlRootElement
public class ImagePageableDto {
	
	private int count;
	private List<Image> images;
	
	public ImagePageableDto() {};
	
	public ImagePageableDto(int count,List<Image> images) {
		this.count = count;
		this.images = images;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ImagePageableDto [count=" + count + ", images=" + images + "]";
	}
}

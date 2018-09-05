package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BuyResolutionDto {

	private long imageId;
	private String resolution;
	
	public BuyResolutionDto() {};
	
	public BuyResolutionDto(long imageId,String resolution) {
		this.imageId = imageId;
		this.resolution = resolution;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "BuyResolutionDto [imageId=" + imageId + ", resolution=" + resolution + "]";
	}
	
	
}

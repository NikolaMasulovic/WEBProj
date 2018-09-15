package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ProjWEB.PROJWEB.Domain.Resolution;

@XmlRootElement
public class ImageUploadDto {

	private long userId;
	private String name;
	private String description;
	private List<Resolution> resolutions;
	private long tagId;
	
	public ImageUploadDto() {};
	
	public ImageUploadDto(long userId,String name,String description,List<Resolution> resolutions,long tagId) {
		
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.resolutions = resolutions;
		this.tagId = tagId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Resolution> getResolutions() {
		return resolutions;
	}

	public void setResolutions(List<Resolution> resolutions) {
		this.resolutions = resolutions;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	@Override
	public String toString() {
		return "ImageUploadDto [userId=" + userId + ", name=" + name + ", description=" + description + ", resolutions="
				+ resolutions + ", tagId=" + tagId + "]";
	}
}

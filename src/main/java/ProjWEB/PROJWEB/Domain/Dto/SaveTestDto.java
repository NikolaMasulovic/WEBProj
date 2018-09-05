package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ProjWEB.PROJWEB.Domain.Resolution;

@XmlRootElement
public class SaveTestDto {
	
	private long userId;
	private String name;
	private String description;
	private List<Resolution> resolutions;
	
	public SaveTestDto() {};
	
	public SaveTestDto(long userId,String name,String description,List<Resolution> resolutions) {
		
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.resolutions = resolutions;
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

	@Override
	public String toString() {
		return "SaveTestDto [userId=" + userId + ", name=" + name + ", description=" + description + ", resolutions="
				+ resolutions + "]";
	}
}

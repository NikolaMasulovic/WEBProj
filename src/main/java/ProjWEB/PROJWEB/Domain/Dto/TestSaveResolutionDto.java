package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import ProjWEB.PROJWEB.Domain.Resolution;

@XmlRootElement
public class TestSaveResolutionDto {
	
	private long userId;
	private List<Resolution> resolutions;
	
	public TestSaveResolutionDto() {};
	
	public TestSaveResolutionDto(long userId,List<Resolution> resolutions) {
		this.userId = userId;
		this.resolutions = resolutions;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Resolution> getResolutions() {
		return resolutions;
	}

	public void setResolutions(List<Resolution> resolutions) {
		this.resolutions = resolutions;
	}

	@Override
	public String toString() {
		return "TestSaveResolutionDto [userId=" + userId + ", resolutions=" + resolutions + "]";
	}
	
	

}

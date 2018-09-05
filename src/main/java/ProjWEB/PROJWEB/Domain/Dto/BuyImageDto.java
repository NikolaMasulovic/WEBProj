package ProjWEB.PROJWEB.Domain.Dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BuyImageDto {

	private String userMail;
	private List<BuyResolutionDto> resolutionsDto;
	
	public BuyImageDto() {};
	
	public BuyImageDto(String userMail,List<BuyResolutionDto> resolutionsDto) {
		this.userMail = userMail;
		this.resolutionsDto = resolutionsDto;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public List<BuyResolutionDto> getResolutionsDto() {
		return resolutionsDto;
	}

	public void setResolutionsDto(List<BuyResolutionDto> resolutionsDto) {
		this.resolutionsDto = resolutionsDto;
	}

	@Override
	public String toString() {
		return "BuyImageDto [userMail=" + userMail + ", resolutionsDto=" + resolutionsDto + "]";
	};	
}

package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserChangePasswordDto {
	
	private long userId;
	private String newPassword;
	
	public UserChangePasswordDto() {};
	
	public UserChangePasswordDto(long userId,String newPassword) {
		this.userId = userId;
		this.newPassword = newPassword;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UserChangePasswordDto [userId=" + userId + ", newPassword=" + newPassword + "]";
	}
}

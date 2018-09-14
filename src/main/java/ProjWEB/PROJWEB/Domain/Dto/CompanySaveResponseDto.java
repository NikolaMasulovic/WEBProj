package ProjWEB.PROJWEB.Domain.Dto;

public class CompanySaveResponseDto {
	
	private String username;
	private String password;
	
	public CompanySaveResponseDto() {};
	
	public CompanySaveResponseDto(String username,String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CompanySaveResponse [username=" + username + ", password=" + password + "]";
	}

}

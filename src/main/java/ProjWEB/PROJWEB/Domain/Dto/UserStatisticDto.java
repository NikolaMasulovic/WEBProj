package ProjWEB.PROJWEB.Domain.Dto;

import ProjWEB.PROJWEB.Domain.User;

public class UserStatisticDto {
	
	private User user;
	private int countHD;
	private int countUHD;
	private int count4K;
	
	public UserStatisticDto() {};
	
	public UserStatisticDto(User user,int countHD,int countUHD,int count4K) {
		this.user = user;
		this.countHD = countHD;
		this.countUHD = countUHD;
		this.count4K = count4K;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCountHD() {
		return countHD;
	}

	public void setCountHD(int countHD) {
		this.countHD = countHD;
	}

	public int getCountUHD() {
		return countUHD;
	}

	public void setCountUHD(int countUHD) {
		this.countUHD = countUHD;
	}

	public int getCount4K() {
		return count4K;
	}

	public void setCount4K(int count4k) {
		count4K = count4k;
	};
	
	@Override
	public String toString() {
		return "UserStatisticDto [user=" + user + ", countHD=" + countHD + ", countUHD=" + countUHD + ", count4K="
				+ count4K + "]";
	}	
}

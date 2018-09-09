package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResolutionStatisticDto {
	
	private String res;
	private int count;
	
	public ResolutionStatisticDto() {};
	
	public ResolutionStatisticDto(String res,int count) {
		this.res = res;
		this.count = count;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ResolutionStatisticDto [res=" + res + ", count=" + count + "]";
	};
}

package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderSaveDto {

	private long userId;
	private long orderId;
	private long imageId;
	private int count;
	
	public OrderSaveDto() {};
	
	public OrderSaveDto(long userId,long orderId,long imageId,int count) {
		this.userId = userId;
		this.orderId = orderId;
		this.imageId = imageId;
		this.count = count;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderSaveDto [userId=" + userId + ", orderId=" + orderId + ", imageId=" + imageId + ", count=" + count
				+ "]";
	}
}

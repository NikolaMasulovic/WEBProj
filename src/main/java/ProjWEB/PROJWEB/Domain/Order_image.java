package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order_image {
	
	private long imageId;
	private long orderId;
	private String resolution;
	
	public Order_image() {};
	
	public Order_image(long imageId,long orderId,String resolution) {
		this.imageId = imageId;
		this.orderId = orderId;
		this.resolution = resolution;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "Order_image [imageId=" + imageId + ", orderId=" + orderId + ", count=" + resolution + "]";
	};
}

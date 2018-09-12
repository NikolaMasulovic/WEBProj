package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	
	private long id;
	private long userId;
	private String orderSatus;
	private String orderDate;
	
	public Order() {};
	
	public Order(long id,long userId,String orderStatus,String orderDate) {
		this.id = id;
		this.userId = userId;
		this.orderSatus = orderStatus;
		this.orderDate = orderDate;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getOrderSatus() {
		return orderSatus;
	}
	public void setOrderSatus(String orderSatus) {
		this.orderSatus = orderSatus;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderSatus=" + orderSatus + ", orderDate=" + orderDate
				+ "]";
	}
}

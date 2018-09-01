package ProjWEB.PROJWEB.Domain.Dto;

import javax.xml.bind.annotation.XmlRootElement;

import ProjWEB.PROJWEB.Domain.User;

@XmlRootElement
public class UserLoginResponse {
	private User user;
	private boolean logged_in;
	private OrderDto order;
	
	public UserLoginResponse() {}
	
	public UserLoginResponse(User user, boolean logged_in,OrderDto order) {
		super();
		this.user = user;
		this.logged_in = logged_in;
		this.order = order;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isLogged_in() {
		return logged_in;
	}
	public void setLogged_in(boolean logged_in) {
		this.logged_in = logged_in;
	}

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "UserLoginResponse [user=" + user + ", logged_in=" + logged_in + ", order=" + order + "]";
	}
}

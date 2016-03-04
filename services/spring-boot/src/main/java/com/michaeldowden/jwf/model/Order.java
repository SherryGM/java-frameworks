package com.michaeldowden.jwf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private final List<OrderItem> items = new ArrayList<OrderItem>();
	private Address address;
	private BigDecimal total;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}

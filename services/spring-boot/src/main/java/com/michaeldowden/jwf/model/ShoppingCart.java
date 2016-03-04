package com.michaeldowden.jwf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private final List<CartItem> items = new ArrayList<CartItem>();
	private Address address;
	private BigDecimal total;

	public List<CartItem> getItems() {
		return items;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}

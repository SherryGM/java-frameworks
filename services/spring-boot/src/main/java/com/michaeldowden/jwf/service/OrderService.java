package com.michaeldowden.jwf.service;

import static com.michaeldowden.jwf.utils.OrderUtils.buildOrder;
import static com.michaeldowden.jwf.utils.OrderUtils.findOrderInHistory;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.web.ResourceNotFoundException;

@Service
@Scope(proxyMode = TARGET_CLASS, value = "session")
public class OrderService {
	private Order order = new Order();

	public void updateShippingAddress(Address address) {
		order.setAddress(address);
	}

	public Address fetchShippingAddress() {
		return order.getAddress();
	}

	public Integer checkout(ShoppingCart cart) {
		Integer orderNumber = buildOrder(order, cart);
		// Clear current order
		order = new Order();
		// Return Order # for lookup
		return orderNumber;
	}

	public Order lookupOrder(Integer orderNumber) {
		Order order = findOrderInHistory(orderNumber);
		if (order == null) {
			throw new ResourceNotFoundException("Cannot find Order #" + orderNumber);
		}
		return order;
	}
}

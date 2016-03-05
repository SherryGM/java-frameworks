package com.michaeldowden.jwf.service;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.michaeldowden.jwf.model.OrderItem;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.utils.CartUtils;
import com.michaeldowden.jwf.utils.ItemNotFoundException;
import com.michaeldowden.jwf.web.ResourceNotFoundException;

@Service
@Scope(proxyMode = TARGET_CLASS, value = "session")
public class CartService {
	private ShoppingCart shoppingCart = new ShoppingCart();

	public ShoppingCart fetchCart() {
		return shoppingCart;
	}

	public void addToCart(OrderItem newItem) {
		CartUtils.addToCart(shoppingCart, newItem);
	}

	public void updateQuantity(Integer itemId, Integer qty) {
		try {
			CartUtils.updateQuantity(shoppingCart, itemId, qty);
		} catch (ItemNotFoundException e) {
			throw new ResourceNotFoundException(e);
		}
	}

	public void removeFromCart(Integer itemId) {
		try {
			CartUtils.removeFromCart(shoppingCart, itemId);
		} catch (ItemNotFoundException e) {
			throw new ResourceNotFoundException(e);
		}
	}
}

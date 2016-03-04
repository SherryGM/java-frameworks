package com.michaeldowden.jwf.service;

import static java.math.BigDecimal.valueOf;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.michaeldowden.jwf.model.CartItem;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.web.ResourceNotFoundException;

@Service
@Scope(proxyMode = TARGET_CLASS, value = "session")
public class CartService {
	private ShoppingCart shoppingCart = new ShoppingCart();

	private void calculateTotal() {
		BigDecimal total = new BigDecimal(0.0);
		for (CartItem item : shoppingCart.getItems()) {
			BigDecimal subtotal = item.getPrice().multiply(valueOf(item.getQty()));
			total = total.add(subtotal);
		}
		shoppingCart.setTotal(total);
	}

	private int findItemInCart(Integer itemId) {
		for (int i = 0; i < shoppingCart.getItems().size(); i++) {
			CartItem item = shoppingCart.getItems().get(i);
			if (item.getId() == itemId) {
				return i;
			}
		}
		return -1;
	}

	public ShoppingCart fetchCart() {
		calculateTotal();
		return shoppingCart;
	}

	public void addToCart(CartItem newItem) {
		int i = findItemInCart(newItem.getId());
		if (i < 0) {
			// Add a new item to the cart
			shoppingCart.getItems().add(newItem);
		} else {
			// Update quantity for existing item
			CartItem cartItem = shoppingCart.getItems().get(i);
			cartItem.setQty(cartItem.getQty() + newItem.getQty());
		}
	}

	public void updateQuantity(Integer itemId, Integer qty) {
		int i = findItemInCart(itemId);
		if (i < 0) {
			throw new ResourceNotFoundException("Item doesn't exist in Shopping Cart");
		} else {
			CartItem cartItem = shoppingCart.getItems().get(i);
			cartItem.setQty(qty);
		}
	}

	public void removeFromCart(Integer itemId) {
		int i = findItemInCart(itemId);
		if (i < 0) {
			throw new ResourceNotFoundException("Item doesn't exist in Shopping Cart");
		} else {
			shoppingCart.getItems().remove(i);
		}
	}
}

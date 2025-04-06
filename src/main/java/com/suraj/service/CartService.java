package com.suraj.service;

import org.springframework.stereotype.Service;

import com.suraj.exception.ProductException;
import com.suraj.model.Cart;
import com.suraj.model.User;
import com.suraj.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
}
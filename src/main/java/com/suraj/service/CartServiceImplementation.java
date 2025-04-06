package com.suraj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.exception.ProductException;
import com.suraj.model.Cart;
import com.suraj.model.CartItem;
import com.suraj.model.Product;
import com.suraj.model.User;
import com.suraj.repository.CartRepository;
import com.suraj.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@Override
	public Cart createCart(User user) {

		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {

		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

		if (isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);

			int price = req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());

			CartItem createdCartItem = cartItemService.createCartItem(cartItem);

			cart.getCartItems().add(createdCartItem);
		}

		return "Item added successfull";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);

		int totalPrice = 0;

		int totalDiscountedPrice = 0;

		int totalItem = 0;

		for (CartItem cartItem : cart.getCartItems()) {
			totalPrice += cartItem.getPrice();
			totalDiscountedPrice += cartItem.getDiscountedPrice();
			totalItem += cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice - totalDiscountedPrice);

		return cartRepository.save(cart);
	}

}
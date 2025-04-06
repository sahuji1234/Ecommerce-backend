package com.suraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.exception.CartItemException;
import com.suraj.exception.ProductException;
import com.suraj.exception.UserException;
import com.suraj.model.CartItem;
import com.suraj.model.User;
import com.suraj.response.ApiResponse;
import com.suraj.service.CartItemService;
import com.suraj.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@DeleteMapping("/{cartItemid}")
	public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable("cartItemid") Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {

		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);

		ApiResponse res = new ApiResponse();
		res.setMessage("Item deleted successfully");
		res.setStatus(true);

		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

	}

	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updatedCartitemHandler(@PathVariable("cartItemId") Long cartItemId,
			@RequestBody CartItem cartItem, @RequestHeader("Authorization") String jwt)
			throws UserException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

		return new ResponseEntity<CartItem>(updatedCartItem, HttpStatus.OK);

	}

}
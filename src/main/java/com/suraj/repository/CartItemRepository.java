package com.suraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.suraj.model.Cart;
import com.suraj.model.CartItem;
import com.suraj.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	
	@Query("select ci from CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product")Product product, @Param("userId")Long userId, @Param("size")String size);

}
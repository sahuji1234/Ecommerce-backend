package com.suraj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suraj.exception.ProductException;
import com.suraj.model.Rating;
import com.suraj.model.User;
import com.suraj.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user)throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
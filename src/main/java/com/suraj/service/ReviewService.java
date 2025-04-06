package com.suraj.service;

import java.util.List;

import com.suraj.exception.ProductException;
import com.suraj.model.Review;
import com.suraj.model.User;
import com.suraj.request.ReviewRequest;

public interface ReviewService {
	
	public Review createReview(ReviewRequest req, User user)throws ProductException;
	public List<Review> getAllReviewName(Long productId);

}
package com.suraj.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.exception.ProductException;
import com.suraj.model.Product;
import com.suraj.model.Review;
import com.suraj.model.User;
import com.suraj.repository.ProductRepository;
import com.suraj.repository.ReviewRepository;
import com.suraj.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {

		Product product = productService.findProductById(req.getProductId());

		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());

		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReviewName(Long productId) {

		return reviewRepository.getAllPrductsReview(productId);
	}

}
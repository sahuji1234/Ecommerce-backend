package com.suraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.exception.ProductException;
import com.suraj.exception.UserException;
import com.suraj.model.Review;
import com.suraj.model.User;
import com.suraj.request.ReviewRequest;
import com.suraj.service.ReviewService;
import com.suraj.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Review review = reviewService.createReview(req, user);
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/product/{productId}")
	public  ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId) throws UserException, ProductException{
		List<Review> reviews = reviewService.getAllReviewName(productId);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.ACCEPTED);
	}

}
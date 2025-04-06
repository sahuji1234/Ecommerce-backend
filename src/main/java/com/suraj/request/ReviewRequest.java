package com.suraj.request;

public class ReviewRequest {
	
	private Long productId;
	private String review;
	public Long getProductId() {
		return productId;
	}
	public String getReview() {
		return review;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public ReviewRequest(Long productId, String review) {
		super();
		this.productId = productId;
		this.review = review;
	}
	public ReviewRequest() {
		
	}
	

}
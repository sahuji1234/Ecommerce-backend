package com.suraj.model;

import com.suraj.user.domain.PaymentStatus;

import jakarta.persistence.Entity;


public class PaymentDetails {

	private String paymentMethod;
	private PaymentStatus status;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpayPaymentId​;
	

	public String getPaymentMethod() {
		return paymentMethod;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}
	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}
	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}
	public String getRazorpayPaymentId​() {
		return razorpayPaymentId​;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}
	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}
	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}
	public void setRazorpayPaymentId​(String razorpayPaymentId​) {
		this.razorpayPaymentId​ = razorpayPaymentId​;
	}
	public PaymentDetails() {
		super();
	}
	
	
	
}
package com.suraj.model;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PaymentInformation {
	
	@Column(name = "cardholder_name")
	private String cardholdername;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	
	@Column(name = "cvv")
	private String cvv;

}
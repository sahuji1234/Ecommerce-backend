package com.suraj.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.suraj.exception.OrderException;
import com.suraj.model.Order;
import com.suraj.repository.OrderRepository;
import com.suraj.response.ApiResponse;
import com.suraj.response.PaymentLinkResponse;
import com.suraj.service.OrderService;
import com.suraj.service.UserService;
import com.suraj.user.domain.OrderStatus;
import com.suraj.user.domain.PaymentStatus;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Value("${razorpay.api.key}")
    private	String apiKey;

	@Value("${razorpay.api.secret}")
	private String apiSecret;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/payments/{orderId}")
	public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException, RazorpayException {

		Order order = orderService.findOrderById(orderId);

		try {
			RazorpayClient razorPay = new RazorpayClient(apiKey, apiSecret);
			JSONObject paymentLinkRequest = new JSONObject();

			paymentLinkRequest.put("amount", order.getTotalPrice() * 100);
			paymentLinkRequest.put("currency", "INR");

			JSONObject customer = new JSONObject();
			customer.put("name", order.getUser().getFirstName());
			customer.put("email", order.getUser().getEmail());

			JSONObject notify = new JSONObject();
			notify.put("sms", true);
			notify.put("email", true);
			paymentLinkRequest.put("notify", notify);
			paymentLinkRequest.put("customer", customer);
			paymentLinkRequest.put("callback_url", "http://localhost:3000/payment/" + orderId);
			paymentLinkRequest.put("callback_method", "get");

			PaymentLink payment = razorPay.paymentLink.create(paymentLinkRequest);

			String paymentLinkId = payment.get("id");
			String paymentLinkUrl = payment.get("short_url");

			PaymentLinkResponse res = new PaymentLinkResponse();
			res.setPayment_link_id(paymentLinkId);
			res.setPayment_link_url(paymentLinkUrl);

			return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);

		} catch (Exception e) {
			throw new RazorpayException("Failed to create payment link: " + e.getMessage());
		}

	}

	  @GetMapping("/payments")
	public ResponseEntity<ApiResponse> redirect(@RequestParam(name = "payment_id") String paymentId,
			@RequestParam(name = "order_id") Long orderId) throws OrderException, RazorpayException {

		Order order = orderService.findOrderById(orderId);
		RazorpayClient razorPay = new RazorpayClient(apiKey, apiSecret);
		try {
			System.out.println("Payment Id"+ paymentId);
			Payment payment = razorPay.payments.fetch(paymentId);
			if (payment.get("status").equals("captured")) {
				order.getPaymentDetails().setPaymentId(paymentId);
				order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
				order.setOrderStatus(OrderStatus.PLACED);
				orderRepository.save(order);
			}
			ApiResponse res = new ApiResponse();
			res.setMessage("your order get palced");
			res.setStatus(true);
			return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

		} catch (Exception e) {
			throw new RazorpayException(e.getMessage());
		}

	}

}
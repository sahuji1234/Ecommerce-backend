package com.suraj.request;

import java.util.HashSet;
import java.util.Set;

import com.suraj.model.Size;

public class CreateProductRequest {
	
	private String title;
	private String description;
	private int price;
	private int discountedPrice;
	private int discountPersent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	private Set<Size> size = new HashSet<>();
	
	private String imageUrl;
	
    private String topLavelCategory;
    private String secondLavelCategory;
    private String thirdLavelCategory;


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public int getPrice() {
		return price;
	}


	public int getDiscountedPrice() {
		return discountedPrice;
	}


	public int getDiscountPersent() {
		return discountPersent;
	}


	public int getQuantity() {
		return quantity;
	}


	public String getBrand() {
		return brand;
	}


	public String getColor() {
		return color;
	}


	public Set<Size> getSize() {
		return size;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public String getTopLavelCategory() {
		return topLavelCategory;
	}


	public String getSecondLavelCategory() {
		return secondLavelCategory;
	}


	public String getThirdLavelCategory() {
		return thirdLavelCategory;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}


	public void setDiscountPersent(int discountPersent) {
		this.discountPersent = discountPersent;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public void setSize(Set<Size> size) {
		this.size = size;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public void setTopLavelCategory(String topLavelCategory) {
		this.topLavelCategory = topLavelCategory;
	}


	public void setSecondLavelCategory(String secondLavelCategory) {
		this.secondLavelCategory = secondLavelCategory;
	}


	public void setThirdLavelCategory(String thirdLavelCategory) {
		this.thirdLavelCategory = thirdLavelCategory;
	}


	@Override
	public String toString() {
		return "CreateProductRequest [title=" + title + ", description=" + description + ", price=" + price
				+ ", discountedPrice=" + discountedPrice + ", discountPersent=" + discountPersent + ", quantity="
				+ quantity + ", brand=" + brand + ", color=" + color + ", size=" + size + ", imageUrl=" + imageUrl
				+ ", topLavelCategory=" + topLavelCategory + ", secondLavelCategory=" + secondLavelCategory
				+ ", tthirdLavelCategory=" + thirdLavelCategory + "]";
	}

	
}
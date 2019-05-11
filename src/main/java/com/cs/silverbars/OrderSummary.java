package com.cs.silverbars;

public class OrderSummary {

	private double price;
	private double quantity;
	
	public OrderSummary(double price, double quantity) {
		this.price = price;
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public double getQuantity() {
		return quantity;
	}		
}

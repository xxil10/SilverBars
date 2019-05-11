package com.cs.silverbars;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestLiveOrderBoard {

	private LiveOrderBoard orderBoard;
	
	@Before
	public void setup() {

		orderBoard = new LiveOrderBoard();
		
		orderBoard.addOrder(new Order("User1", OrderType.SELL, 3.5, 306.00));
		orderBoard.addOrder(new Order("User2", OrderType.SELL, 1.2, 310.00));
		orderBoard.addOrder(new Order("User3", OrderType.SELL, 1.5, 307.00));
		orderBoard.addOrder(new Order("USer4", OrderType.SELL, 2.0, 306.00));

		orderBoard.addOrder(new Order("User1", OrderType.BUY, 3.5, 306.00));
		orderBoard.addOrder(new Order("User2", OrderType.BUY, 1.2, 310.00));
		orderBoard.addOrder(new Order("User3", OrderType.BUY, 1.5, 307.00));
		orderBoard.addOrder(new Order("USer4", OrderType.BUY, 2.0, 306.00));

	}
	
	@Test
	public void whenOrderAddedShouldAddToOrders() {
	
		Order order = new Order("User1", OrderType.BUY, 3.5, 306.00);
		
		orderBoard.addOrder(order);
		
		assertEquals(5, orderBoard.getBuyOrders().size());
		assertEquals(4, orderBoard.getSellOrders().size());
	}	

	@Test
	public void whenOrderRemovedShouldRemoveFromOrders() {
			
		orderBoard.removeOrder(new Order("User1", OrderType.SELL, 3.5, 306.00));
		
		assertEquals(4, orderBoard.getBuyOrders().size());
		assertEquals(3, orderBoard.getSellOrders().size());
	}
	
	@Test
	public void whenGetOrdersShouldSummarizeCorrectly() {

		List<OrderSummary> summary = orderBoard.getSellSummary();
		
		assertEquals(3, summary.size());
		assertEquals(1.2, summary.get(0).getQuantity(), 0.001);
		assertEquals(1.5, summary.get(1).getQuantity(), 0.001);
		assertEquals(5.5, summary.get(2).getQuantity(), 0.001);		
	}

	@Test
	public void whenGetBuyOrdersShouldOrderCorrectly() {

		List<OrderSummary> summary = orderBoard.getBuySummary();
		
		assertEquals(3, summary.size());
		
		assertTrue(summary.get(0).getPrice() < summary.get(1).getPrice());
		assertTrue(summary.get(1).getPrice() < summary.get(2).getPrice());
	}

	
	@Test
	public void whenGetSellOrdersShouldOrderCorrectly() {

		List<OrderSummary> summary = orderBoard.getSellSummary();
		
		assertEquals(3, summary.size());
		
		assertTrue(summary.get(0).getPrice() > summary.get(1).getPrice());
		assertTrue(summary.get(1).getPrice() > summary.get(2).getPrice());
	}		
}
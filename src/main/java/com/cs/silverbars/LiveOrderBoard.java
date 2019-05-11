package com.cs.silverbars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LiveOrderBoard {

	private List<Order> orders = Collections.synchronizedList(new ArrayList<Order>());
	
	private static Comparator<Map.Entry<Double, Double>> comparitor = Comparator.comparing(Map.Entry::getKey);
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {		
		orders.remove(order);
	}
	
	public List<Order> getBuyOrders() {
		
		return orders.stream().filter(i -> i.getBuySell() == OrderType.BUY)
				              .collect(Collectors.toList());
	}

	public List<Order> getSellOrders() {
		
		return orders.stream().filter(i -> i.getBuySell() == OrderType.SELL)
				              .collect(Collectors.toList());
	}

	public List<OrderSummary> getBuySummary() {
		
		return getOrdersOfType(OrderType.BUY).entrySet().stream()
					.sorted(comparitor)
					.map(e -> new OrderSummary(e.getKey(), e.getValue()))
					.collect(Collectors.toList());
	}

	public List<OrderSummary> getSellSummary() {
		
		return getOrdersOfType(OrderType.SELL).entrySet().stream()
				 .sorted(comparitor.reversed())
				 .map(e -> new OrderSummary(e.getKey(), e.getValue()))
				 .collect(Collectors.toList());
	}	
	
	private Map<Double, Double> getOrdersOfType(OrderType type) {
		
		Map<Double, Double> map = new HashMap<Double, Double>();
				
		orders.stream()
			  .filter(t -> t.getBuySell() == type)
			  .forEach(o -> {
				  map.computeIfPresent(o.getPrice(), (k, v) -> v += o.getQuantity());
				  map.computeIfAbsent(o.getPrice(), (v) -> o.getQuantity());
			  });
		
		return map;
	}	
}
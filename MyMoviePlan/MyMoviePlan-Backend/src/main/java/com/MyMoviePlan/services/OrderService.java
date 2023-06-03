package com.MyMoviePlan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MyMoviePlan.model.Order;
import com.MyMoviePlan.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public Order saveOrder(Order order) {
		
		return orderRepository.save(order);
	}
	
	public List<Order> listOfOrders() {
		
		return orderRepository.findAll();
	}
	
	public List<Order> getOrderDetails(int id) {
		 return orderRepository.findByCustomerId(id,Sort.by(Sort.Direction.DESC,"id"));
	}
	

}

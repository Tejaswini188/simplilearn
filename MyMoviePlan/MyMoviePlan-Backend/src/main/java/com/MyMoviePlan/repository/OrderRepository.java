package com.MyMoviePlan.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MyMoviePlan.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("SELECT t FROM Order t WHERE t.customerId=?1")
	List<Order> findByCustomerId(int id, Sort sort);

}

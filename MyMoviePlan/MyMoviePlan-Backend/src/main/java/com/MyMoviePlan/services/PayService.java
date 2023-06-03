package com.MyMoviePlan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyMoviePlan.model.PaymentPOJO;
import com.MyMoviePlan.repository.PaymentRepository;





@Service
public class PayService {

	@Autowired
	PaymentRepository paymentRepository;
	
	public void savePay(PaymentPOJO pay) {
		paymentRepository.save(pay);
	}
	
	
	public List<PaymentPOJO> listPay() {
		 return paymentRepository.findAll();
	}
	
	public PaymentPOJO getPayDetails(String id) {
		 return paymentRepository.findByDescriptionLike(id);
	}
	
	
	
	
	
}

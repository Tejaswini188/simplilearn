package com.kitchenstory.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.models.Admin;
import com.kitchenstory.models.Products;
import com.kitchenstory.services.ProductsService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api/product/")
public class ProductsController {
	@Autowired
	private ProductsService productsService;

	ProductsController(ProductsService productsService) {
		this.productsService = productsService;
	}

	@Autowired
	JdbcTemplate jdbc;

	@GetMapping("all")
	public List<Products> getAllProducts() throws SQLException {

		return productsService.getAllProducts();
	}

	@GetMapping("search/")
	public Products findProductByName(@RequestParam("name") String product_name) throws SQLException {

		return productsService.getProductByName(product_name);
	}

	@GetMapping("{product_id}")
	public Products findProductById(@PathVariable("product_id") int product_id) throws SQLException {

		return productsService.getProductById(product_id);
	}

	@PostMapping("add")
	public String addProduct(@RequestBody Products product) {
		productsService.addProduct(product);
		return "Product has been created";
	}


	
	@DeleteMapping("delete/{product_id}")
	public String deleteProduct(@PathVariable("product_id") int product_id) {
		productsService.deleteProduct(product_id);
		return "Product has been deleted..";
	}

	@PostMapping("pay")
	public String pay(@RequestParam("bank_name") String bank_name, @RequestParam("card_number") String card_number,
			@RequestParam("expiration_date") String expiration_date, @RequestParam("pin") String pin) {
		String paymentResponse = null;
		
		boolean paymentSuccess = productsService.payment(bank_name, card_number, expiration_date, pin);
		if (paymentSuccess) {

			paymentResponse = "paid successfully..";
		} else {

			paymentResponse = "Incorrect card number or pin..";
		}
		return paymentResponse;
	}

	@GetMapping("confirm")
	public String confirm(@RequestParam("confirmation") String confirm) {
		String confirmation = "confirmed";
		if (!(confirm.equals("yes"))) {
			confirmation = "not confirmed";
		}
		return confirmation;
	}
}

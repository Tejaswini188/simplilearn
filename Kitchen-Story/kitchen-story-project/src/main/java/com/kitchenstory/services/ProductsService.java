package com.kitchenstory.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.daos.ProductsDao;
import com.kitchenstory.models.Products;

@Service
public class ProductsService {
	@Autowired
	private ProductsDao productsRepository;

	ProductsService(ProductsDao productsRepository) {
		super();
		this.productsRepository = productsRepository;
	}

	public List<Products> getAllProducts() throws SQLException {
		return this.productsRepository.getAllProducts();
	}

	public Products getProductByName(String product_name) throws SQLException {
		return this.productsRepository.getProductByName(product_name);
	}

	public Products getProductById(int product_id) throws SQLException {
		return this.productsRepository.getProductById(product_id);
	}

	public int addProduct(Products product) {
		return this.productsRepository.addProduct(product);
	}

	public int deleteProduct(int product_id) {
		return this.productsRepository.deleteProduct(product_id);
	}

	public boolean payment(String bank_name, String card_number, String expiration_date, String pin) {
		boolean success = true;
		if (!(card_number.length() == 10 && pin.length() == 4)) {
			return !success;
		}
		return success;
	}
}

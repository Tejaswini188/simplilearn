package com.kitchenstory.daos;

import java.sql.SQLException;
import java.util.List;

import com.kitchenstory.models.Products;

public interface ProductsDao {
	List<Products> getAllProducts() throws SQLException;
	Products getProductByName(String product_name) throws SQLException;
	Products getProductById(int product_id)throws SQLException;
	int addProduct(Products product);
	int deleteProduct(int product_id);
}

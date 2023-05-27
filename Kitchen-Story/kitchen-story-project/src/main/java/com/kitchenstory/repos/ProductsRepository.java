package com.kitchenstory.repos;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kitchenstory.daos.ProductsDao;
import com.kitchenstory.models.Products;

@Repository
public class ProductsRepository implements ProductsDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<Products> getAllProducts() throws SQLException {

		return jdbc.query("select * from products", BeanPropertyRowMapper.newInstance(Products.class));
	}

	@Override
	public Products getProductByName(String product_name) throws SQLException {
		return jdbc.queryForObject("select * from products where product_name like ?",
				BeanPropertyRowMapper.newInstance(Products.class), "%"+product_name+"%");
	}
	@Override
	public Products getProductById(int product_id) throws SQLException {
		return jdbc.queryForObject("select * from products where product_id =?",
				BeanPropertyRowMapper.newInstance(Products.class), product_id);
	}
	@Override
	public int addProduct(Products product) {
	return	jdbc.update(
				"insert into products(product_name, url, MSRP)values(?,?,?)",
				new Object[] { product.getProduct_name(),  product.getUrl(), product.getMSRP() });
	}
	
	@Override
	public int deleteProduct(int product_id) {
	return	jdbc.update("delete  from products where product_id =?", product_id);
	}
}

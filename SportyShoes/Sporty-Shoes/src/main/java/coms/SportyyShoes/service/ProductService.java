package coms.SportyyShoes.service;

import java.util.List;

import coms.SportyyShoes.model.Product;
import coms.SportyyShoes.model.ProductCategory;


public interface ProductService {

	public Product getProductById(Long productCode);
	
	public Product getProductByProductCategory(ProductCategory productCategory);
	
	public void updateProduct(Product product);
	
	public Product saveProduct(Product product);
	public void deleteProduct(Long productCode);
	
	
	public List<Product> getAllProducts();
	
	
}

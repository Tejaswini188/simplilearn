package coms.SportyyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.SportyyShoes.model.ProductCategory;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Long >{

}

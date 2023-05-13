package coms.SportyyShoes.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import coms.SportyyShoes.model.PurchaseOrder;



public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	
	

}

package coms.SportyyShoes.service;

import java.util.List;

import coms.SportyyShoes.model.PurchaseItem;
import coms.SportyyShoes.model.PurchaseOrder;
import coms.SportyyShoes.model.User;



public interface PurchaseItemService {

	public PurchaseItem getPurchaseItemById(Long id);

	public List<PurchaseItem> getAllItemsByPurchaseOrder(PurchaseOrder order);

	public List<PurchaseItem> getAllPurchaseItemByUserId(User userId);

	public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem);

	public List<PurchaseItem> getAllPurchaseItemList();

}

package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    List<ItemInventory> getItemsLowerBoundery();
    List<ItemInventory> getItemsUpperBoundery();
    List<User> sendMessageUpperBoundThreshold();
    List<User> sendMessageLowerBoundThreshold();
    void updateItemInventoryBuy(ItemInventory model);
}
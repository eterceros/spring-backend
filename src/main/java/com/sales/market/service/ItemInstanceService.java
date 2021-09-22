/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;

import java.math.BigDecimal;
import java.util.List;

public interface ItemInstanceService extends GenericService<ItemInstance> {
    List<ItemInstance> findByItemInstanceStatus(ItemInstanceStatus itemInstanceStatus);
    int countItemInstancesStatus(ItemInstanceStatus itemInstanceStatus, Item item);
    double countPriceItemInstancesStatus(Item item,ItemInstanceStatus itemInstanceStatus);
    List<ItemInstance> findItemInstancesByItem(Item item,ItemInstanceStatus itemInstanceStatus);
   // void update(Item model,int quantity);
    //String Skus(Item item, BigDecimal bigDecimal, BigDecimal quantity);
}

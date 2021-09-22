/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {

    List<ItemInstance> findByItemInstanceStatus(@Param("itemInstanceStatus") ItemInstanceStatus itemInstanceStatus);

    @Query("select count(itemInstance.itemInstanceStatus)From ItemInstance itemInstance  WHERE itemInstance.itemInstanceStatus = :itemInstanceStatus and itemInstance.item= :item")
    int countItemInstancesStatus(@Param("itemInstanceStatus") ItemInstanceStatus itemInstanceStatus, @Param("item") Item item);
    @Query("select itemInstance From ItemInstance itemInstance  WHERE itemInstance.itemInstanceStatus = :itemInstanceStatus and itemInstance.item= :item")
   List<ItemInstance> findItemInstancesByItem(Item item,ItemInstanceStatus itemInstanceStatus);
}

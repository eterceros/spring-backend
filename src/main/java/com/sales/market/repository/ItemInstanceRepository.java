/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {
    @Query(" select Count(itemInstance.itemInstanceStatus) From ItemInstance itemInstance  WHERE itemInstance.itemInstanceStatus = :itemInstanceStatus")
    List<ItemInstance> countItemInstances(@Param("itemInstance") ItemInstanceStatus itemInstanceStatus);
}

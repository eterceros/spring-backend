/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;

import java.util.List;

public interface ItemInstanceService extends GenericService<ItemInstance> {
    List<ItemInstance> countItemInstances(ItemInstanceStatus itemInstanceStatus);
}

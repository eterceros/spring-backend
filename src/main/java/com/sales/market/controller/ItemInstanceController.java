/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.model.ItemInstance;
import com.sales.market.service.ItemInstanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iteminstances")
public class ItemInstanceController extends GenericController<ItemInstance, ItemInstanceDto> {
    private ItemInstanceService service;

    public ItemInstanceController(ItemInstanceService service) {
        this.service = service;
    }

    @Override
    protected ItemInstanceService getService() {
        return service;
    }

    @GetMapping("/findItemList")
    public List<ItemInstanceDto> findByItemInstanceStatus(@RequestBody ItemInstanceDto itemInstanceDto) {
        List<ItemInstance> itemInstance=service.findByItemInstanceStatus(itemInstanceDto.getItemInstanceStatus());
        return toDto(itemInstance);
    }
}

/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.dto.UserDto;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.User;
import com.sales.market.service.ItemInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sales.market.model.ItemInstanceStatus;

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

    @GetMapping()
    public List<ItemInstanceDto> item(@RequestBody ItemInstanceStatus itemInstanceStatus) {
        List<ItemInstance> itemInstance=service.countItemInstances(itemInstanceStatus);
        return toDto(itemInstance);
    }
}

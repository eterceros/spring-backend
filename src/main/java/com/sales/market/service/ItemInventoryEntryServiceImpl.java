package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryEntryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService {
    private final ItemInventoryEntryRepository repository;
    private final ItemInstanceServiceImpl itemInstanceService;
    private final ItemServiceImpl itemService;
    private final ItemInventoryServiceImpl itemInventoryService;


    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository repository, ItemInstanceServiceImpl itemInstanceService, ItemServiceImpl itemService, ItemInventoryServiceImpl itemInventoryService) {
        this.repository = repository;
        this.itemInstanceService = itemInstanceService;
        this.itemService = itemService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return repository;
    }

    @Override
    public ItemInventoryEntry save(ItemInventoryEntry model) {
        ItemInventory itemInventory=itemInventoryService.findById(model.getItemInventory().getId());
        model.setItemInventory(itemInventory);
        model.setItemInstanceSkus(itemInstanceService.Skus(itemInventory.getItem(),model.getQuantity()));
        model.setMovementType(model.getMovementType());
        movementType(model.getMovementType(),model.getQuantity(),itemInventory);
        return super.save(model);
    }
    public void movementType(MovementType movementType,BigDecimal quantity,ItemInventory itemInventory){
        if(movementType.name().equals(MovementType.SALE)){
         //   itemInstanceService.update(itemInventory.getItem(),quantity.intValue());
            itemInventoryService.save(itemInventory);
        }

    }
}
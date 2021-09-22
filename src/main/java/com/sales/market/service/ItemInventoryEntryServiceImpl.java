package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryEntryRepository;
import org.springframework.stereotype.Service;


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
        if(model.getMovementType()==MovementType.BUY){
           itemInstanceService.updateItemInstaceBuy(itemInventory.getItem().getId(),model.getPrice(),model.getItemInstanceSkus(),model.getQuantity());
           itemInventoryService.updateItemInventoryBuy(itemInventory);
           model.setItemInventory(itemInventory);
        }
        if(model.getMovementType()==MovementType.REMOVED){
            itemInstanceService.updateItemInstanceRemoved(itemInventory.getItem(),model.getItemInstanceSkus(),model.getQuantity());
            itemInventoryService.updateItemInventoryRemovedAndSale(itemInventory);
            model.setItemInventory(itemInventory);
        }
        if (model.getMovementType()==MovementType.SALE){
            itemInstanceService.updateItemInstanceSale(itemInventory.getItem(),model.getItemInstanceSkus(),model.getQuantity());
            itemInventoryService.updateItemInventoryRemovedAndSale(itemInventory);
            model.setItemInventory(itemInventory);
        }
        return super.save(model);
    }
}
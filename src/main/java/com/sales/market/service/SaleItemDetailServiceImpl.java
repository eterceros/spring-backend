package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.SaleItemDetailRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaleItemDetailServiceImpl extends GenericServiceImpl<SaleItemDetail> implements SaleItemDetailService {
    private final SaleItemDetailRepository repository;
    private final SaleServiceImpl saleService;
    private final ItemInventoryEntryServiceImpl itemInventoryEntryService;
    private final ItemInstanceServiceImpl itemInstanceService;

    public SaleItemDetailServiceImpl(SaleItemDetailRepository repository, SaleServiceImpl saleService, ItemInventoryEntryServiceImpl itemInventoryEntryService, ItemInstanceServiceImpl itemInstanceService) {
        this.repository = repository;
        this.saleService = saleService;
        this.itemInventoryEntryService = itemInventoryEntryService;
        this.itemInstanceService = itemInstanceService;
    }

    @Override
    protected GenericRepository<SaleItemDetail> getRepository() {
        return repository;
    }

    @Override
    public SaleItemDetail save(SaleItemDetail model) {
       Sale sale =saleService.findById(model.getSale().getId());
       ItemInventoryEntry itemInventoryEntry=itemInventoryEntryService.findById(model.getItemInventoryEntry().getId());
       model.setSale(sale);
       model.setItemInventoryEntry(itemInventoryEntry);
       model.setUnitCost(getPriceItemInstance(itemInventoryEntry.getItemInventory().getItem()));
       model.setQuantity(itemInventoryEntry.getQuantity());
       model.setSkus(itemInventoryEntry.getItemInstanceSkus());
       model.setItemCode(itemInventoryEntry.getItemInventory().getItem().getCode());
       model.setItemName(itemInventoryEntry.getItemInventory().getItem().getName());
       model.setDateSold(sale.getDate());
       model.setEmpleado(sale.getEmployee().getFirstName()+" " +sale.getEmployee().getLastName());
        return super.save(model);
    }
        public BigDecimal getPriceItemInstance(Item item){
        ItemInstance itemInstance=itemInstanceService.findById(item.getId());
        return  itemInstance.getPrice();

    }
}
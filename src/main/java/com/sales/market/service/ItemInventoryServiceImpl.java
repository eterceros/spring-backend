package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.User;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;
    private final ItemInstanceServiceImpl itemInstanceService;
    private final ItemServiceImpl itemService;
    private final UserServiceImpl userService;
    private final EmailService emailService;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository, ItemInstanceServiceImpl itemInstanceService, ItemServiceImpl itemService, UserServiceImpl userService, EmailService emailService) {
        this.repository = repository;
        this.itemInstanceService = itemInstanceService;
        this.itemService = itemService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    @Override
    public ItemInventory save(ItemInventory model) {
        Item item=itemService.findById(model.getItem().getId());
        model.setItem(item);
        model.setStockQuantity(BigDecimal.valueOf(itemInstanceService.countItemInstancesStatus(ItemInstanceStatus.AVAILABLE, model.getItem())));
        model.setTotalPrice(BigDecimal.valueOf(itemInstanceService.countPriceItemInstancesStatus(model.getItem(),ItemInstanceStatus.AVAILABLE)));
        return super.save(model);

    }

    public void updateItemInventoryRemovedAndSale(ItemInventory model){
        double AvailableDiscared=itemInstanceService.countPriceItemInstancesStatus(model.getItem(),ItemInstanceStatus.AVAILABLE)+
                itemInstanceService.countPriceItemInstancesStatus(model.getItem(),ItemInstanceStatus.DISCARDED);
        model.setStockQuantity(BigDecimal.valueOf(itemInstanceService.countItemInstancesStatus(ItemInstanceStatus.AVAILABLE, model.getItem())));
        model.setTotalPrice(BigDecimal.valueOf(AvailableDiscared));
        repository.save(model);

    }
    @Override
    public void updateItemInventoryBuy(ItemInventory model){
        model.setStockQuantity(BigDecimal.valueOf(itemInstanceService.countItemInstancesStatus(ItemInstanceStatus.AVAILABLE, model.getItem())));
        model.setTotalPrice(BigDecimal.valueOf(itemInstanceService.countPriceItemInstancesStatus(model.getItem(),ItemInstanceStatus.AVAILABLE)));
        repository.save(model);

    }


    @Override
    public List<ItemInventory> getItemsLowerBoundery() {
        return repository.geItemsLowerBoundery();
    }

    @Override
    public List<ItemInventory> getItemsUpperBoundery() {
        return repository.geItemsUpperBoundery();
    }
    @Async
    @Override
    public List<User> sendMessageUpperBoundThreshold() {
        List<User> usersSupervisores = userService.findUsersSupervisor();
        for (User users:usersSupervisores){
            emailService.sendSimpleMessage(users.getEmail(),"Upper Bound Threshold Item",""+messageUpperBoundThreshold());
        }
        return usersSupervisores;

    }

    @Async
    @Override
    public List<User> sendMessageLowerBoundThreshold() {
        List<User> usersSupervisores = userService.findUsersSupervisor();
        for (User users:usersSupervisores){
            emailService.sendSimpleMessage(users.getEmail(),"Upper Bound Threshold Item",""+messageLowerBoundThreshold());
        }
        return usersSupervisores;

    }
    public String messageUpperBoundThreshold() {
        String messages = "";
        for (ItemInventory itemInventory : getItemsUpperBoundery()) {
            messages = "Name Item : " + itemInventory.getItem().getName()
                   +"  " + "Stock Quantity : " + itemInventory.getStockQuantity() + "\n";

        }
        return messages;
    }
    public String messageLowerBoundThreshold() {
        String messages = "";
        for (ItemInventory itemInventory : getItemsLowerBoundery()) {
            messages = "Name Item : " + itemInventory.getItem().getName()
                    +"  " + "Stock Quantity : " + itemInventory.getStockQuantity() + "\n";

        }
        return messages;
    }


}
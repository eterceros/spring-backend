/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.model.MovementType;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }

    @Override
    public ItemInstance bunchSave(ItemInstance itemInstance) {
        // here make all objects save other than this resource
        if (itemInstance.getItem() != null) {
            // todo habria que distinguir si permitiremos guardar y  actualizar o ambos mitando el campo id
            itemService.save(itemInstance.getItem());
        }
        return super.bunchSave(itemInstance);
    }

    @Override
    public ItemInstance save(ItemInstance model) {
        Item item= itemService.findById(model.getItem().getId());
        model.setItem(item);
        return super.save(model);
    }

    @Override
    public List<ItemInstance> findByItemInstanceStatus(ItemInstanceStatus itemInstanceStatus){
        return repository.findByItemInstanceStatus(itemInstanceStatus);
    }
    public int countItemInstancesStatus(ItemInstanceStatus itemInstanceStatus, Item item){
        return repository.countItemInstancesStatus(itemInstanceStatus,item);
    }
    public double countPriceItemInstancesStatus(Item item){
        double totalPrice=0;
       for(ItemInstance itemInstance1:findItemInstancesByItem(item)){
               totalPrice=totalPrice+itemInstance1.getPrice();

       }
       return totalPrice;
    }

    public String Skus(Item item,BigDecimal quantity){
        String skus="";
        int cont=0;
            for(ItemInstance itemInstance:findItemInstancesByItem(item)){
                if(cont<quantity.intValue()){
                        skus=skus+itemInstance.getIdentifier()+",";
                        cont=cont+1;
                }
            }
        return skus;
    }

    public List<ItemInstance> findItemInstancesByItem(Item item){
        return repository.findItemInstancesByItem(item,ItemInstanceStatus.AVAILABLE);
    }

/*

    public void update(Item model,int quantity) {
        int cont=0;
        for (ItemInstance itemInstance:findItemInstancesByItem(model)){
            if(cont<quantity){
                itemInstance.setItemInstanceStatus(ItemInstanceStatus.SOLD);
                repository.save(itemInstance);
            }
        }
    }

     */
}

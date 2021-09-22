/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.model.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public double countPriceItemInstancesStatus(Item item,ItemInstanceStatus itemInstanceStatus){
        double totalPrice=0;
       for(ItemInstance itemInstance1:findItemInstancesByItem(item,itemInstanceStatus)){
               totalPrice=totalPrice+itemInstance1.getPrice().intValue();

       }
       return totalPrice;
    }

    public String Skus(Item item,BigDecimal quantity){
        String skus="";
        int cont=0;
            for(ItemInstance itemInstance:findItemInstancesByItem(item,ItemInstanceStatus.SOLD)){
                if(cont<quantity.intValue()){
                        skus=skus+itemInstance.getIdentifier()+",";
                        cont=cont+1;
                }
            }
        return skus;
    }

    public List<ItemInstance> findItemInstancesByItem(Item item,ItemInstanceStatus itemInstanceStatus){
        return repository.findItemInstancesByItem(item,itemInstanceStatus);
    }
    public void updateItemInstaceBuy(Long id,BigDecimal price,String skus,BigDecimal quantity){
        Item item=itemService.findById(id);
        String[]skuslist=skus.split(",");
        if(quantity.intValue()==skuslist.length){
            for (int i=0;i<skuslist.length;i++){
                ItemInstance itemInstance=new ItemInstance();
                itemInstance.setItem(item);
                itemInstance.setIdentifier(skuslist[i]);
                itemInstance.setFeatured(false);
                itemInstance.setPrice(price);
                itemInstance.setItemInstanceStatus(ItemInstanceStatus.AVAILABLE);
                repository.save(itemInstance);
            }
        }else {
            logger.error("La cantidad y el skus deben de ser igual ya que un sku es unico");
        }
    }
    public void updateItemInstanceRemoved(Item item,String skus, BigDecimal quantity){
        String[]skuslist=skus.split(",");
        if(quantity.intValue()==skuslist.length){
            for (int i=0;i<skuslist.length;i++){
                if(ifExistsItemInstance(skuslist[i],item)==true){
                    ItemInstance itemInstance=findById(findSkus(skuslist[i],item));
                    itemInstance.setIdentifier((skuslist[i]));
                    itemInstance.setFeatured(true);
                    itemInstance.setItemInstanceStatus(ItemInstanceStatus.DISCARDED);
                    repository.save(itemInstance);
                }
            }
        }else {
            logger.error("La cantidad y el skus deben de ser igual ya que un sku es unico");
        }
    }
    public Long findSkus(String sku,Item item){
        Long id=0L;
        List<ItemInstance>itemInstanceList=findItemInstancesByItem(item,ItemInstanceStatus.AVAILABLE);
        for(ItemInstance itemInstance:itemInstanceList){
            if(itemInstance.getIdentifier().equals(sku)) {
                id = itemInstance.getId();
            }

        }
        return id;
    }
    public boolean ifExistsItemInstance(String sku,Item item){
        boolean exists=false;
        List<ItemInstance>itemInstanceList=findItemInstancesByItem(item,ItemInstanceStatus.AVAILABLE);
        for(ItemInstance itemInstance:itemInstanceList){
            if(itemInstance.getIdentifier().equals(sku)){
                exists=true;
            }

        }

        return exists;
    }
    public void updateItemInstanceSale(Item item,String skus, BigDecimal quantity){
        String[]skuslist=skus.split(",");
        if(quantity.intValue()==skuslist.length){
            for (int i=0;i<skuslist.length;i++){
                if(ifExistsItemInstance(skuslist[i],item)==true){
                    ItemInstance itemInstance=findById(findSkus(skuslist[i],item));
                    itemInstance.setIdentifier((skuslist[i]));
                    itemInstance.setFeatured(true);
                    itemInstance.setItemInstanceStatus(ItemInstanceStatus.SOLD);
                    repository.save(itemInstance);
                }
            }
        }else {
            logger.error("La cantidad y el skus deben de ser igual ya que un sku es unico");
        }
    }


}

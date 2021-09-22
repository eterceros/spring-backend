package com.sales.market.service;

import com.sales.market.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemInventoryEntryServiceImplTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemInventoryService itemInventoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemInstanceService itemInstanceService;
    @Autowired
    private ItemInventoryEntryService itemInventoryEntryService;


    @Test
    void RealizarCompra() {
        Category category = new Category();
        category.setName("CAT1-NAME");
        category.setCode("CAT1-CODE");
        categoryService.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("BEVERAGE");
        subCategory.setCode("BEVERAGE-CODE");
        subCategory.setCategory(category);
        subCategoryService.save(subCategory);

        Item item = new Item();
        item.setCode("B-PEPSI");
        item.setName("PEPSI");
        item.setSubCategory(subCategory);
        itemService.save(item);
        ItemInstance itemInstance=new ItemInstance();
        itemInstance.setItem(item);
        itemInstance.setFeatured(true);
        itemInstance.setIdentifier("SKU-77721106006189");
        itemInstance.setPrice(new BigDecimal("10"));
        itemInstance.setItemInstanceStatus(ItemInstanceStatus.AVAILABLE);
        itemInstanceService.save(itemInstance);
        ItemInstance itemInstance1=new ItemInstance();
        itemInstance1.setItem(item);
        itemInstance1.setFeatured(true);
        itemInstance1.setIdentifier("SKU-777211060061890");
        itemInstance1.setPrice(new BigDecimal("10"));
        itemInstance1.setItemInstanceStatus(ItemInstanceStatus.SOLD);
        itemInstanceService.save(itemInstance1);

        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setLowerBoundThreshold(new BigDecimal(5));
        itemInventory.setUpperBoundThreshold(new BigDecimal(10));
        itemInventoryService.save(itemInventory);

        ItemInventory itemInventory_f = itemInventoryService.findById(itemInventory.getId());



        ItemInventoryEntry model = new ItemInventoryEntry();
        model.setItemInventory(itemInventory_f);
        model.setMovementType(MovementType.BUY);
        model.setPrice(new BigDecimal("8"));
        model.setQuantity(new BigDecimal("5"));
        model.setItemInstanceSkus("SKU-77721106006191,SKU-77721106006192,SKU-77721106006193,SKU-77721106006194,SKU-77721106006195");
        itemInventoryEntryService.save(model);
        List<ItemInstance> itemInstances = itemInstanceService.findItemInstancesByItem(item, ItemInstanceStatus.AVAILABLE);
        System.out.println("----------------------------" + itemInstances.size());
        assertEquals(6, itemInstances.size());

    }

}
/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import com.sales.market.dto.ItemInventoryDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ItemInventory extends ModelBase<ItemInventoryDto>{

    @OneToOne(optional = false)
    private Item item;
    private BigDecimal stockQuantity;
    private BigDecimal lowerBoundThreshold;
    private BigDecimal upperBoundThreshold;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "itemInventory")
    private List<ItemInventoryEntry> itemInventoryEntryList;

    public List<ItemInventoryEntry> getItemInventoryEntryList() {
        return itemInventoryEntryList;
    }

    public void setItemInventoryEntryList(List<ItemInventoryEntry> itemInventoryEntryList) {
        this.itemInventoryEntryList = itemInventoryEntryList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getLowerBoundThreshold() {
        return lowerBoundThreshold;
    }

    public void setLowerBoundThreshold(BigDecimal lowerBoundThreshold) {
        this.lowerBoundThreshold = lowerBoundThreshold;
    }

    public BigDecimal getUpperBoundThreshold() {
        return upperBoundThreshold;
    }

    public void setUpperBoundThreshold(BigDecimal upperBoundThreshold) {
        this.upperBoundThreshold = upperBoundThreshold;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

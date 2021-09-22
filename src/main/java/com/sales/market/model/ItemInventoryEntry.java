/**
 * @author: Edson A. Terceros T.
 */
package com.sales.market.model;

import com.sales.market.dto.ItemInventoryEntryDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ItemInventoryEntry extends ModelBase<ItemInventoryEntryDto> {

    @ManyToOne(optional = false)
    private ItemInventory itemInventory;
    private MovementType movementType;
    private BigDecimal quantity;
    private String itemInstanceSkus;
    private BigDecimal price;

    @OneToMany(mappedBy = "itemInventoryEntry")
    private List<SaleItemDetail> saleItemDetails;

    public List<SaleItemDetail> getSaleItemDetails() {
        return saleItemDetails;
    }

    public void setSaleItemDetails(List<SaleItemDetail> saleItemDetails) {
        this.saleItemDetails = saleItemDetails;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getItemInstanceSkus() {
        return itemInstanceSkus;
    }

    public void setItemInstanceSkus(String itemInstanceSkus) {
        this.itemInstanceSkus = itemInstanceSkus;
    }

}

package com.sales.market.model;

import com.sales.market.dto.SaleItemDetailDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class SaleItemDetail extends ModelBase<SaleItemDetailDto>  {
   @ManyToOne
   private Sale sale;
   @ManyToOne
   private ItemInventoryEntry itemInventoryEntry;

    private BigDecimal unitCost;

    private BigDecimal quantity;

    private String skus;

    private BigDecimal totalAmount;

    private String itemName;

    private String itemCode;

    private Date dateSold;
    private String empleado;

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public ItemInventoryEntry getItemInventoryEntry() {
        return itemInventoryEntry;
    }

    public void setItemInventoryEntry(ItemInventoryEntry itemInventoryEntry) {
        this.itemInventoryEntry = itemInventoryEntry;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSkus() {
        return skus;
    }

    public void setSkus(String skus) {
        this.skus = skus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }
}

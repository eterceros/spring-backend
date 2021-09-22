package com.sales.market.dto;


import com.sales.market.model.SaleItemDetail;

import java.math.BigDecimal;
import java.util.Date;

public class SaleItemDetailDto extends DtoBase<SaleItemDetail> {
    private SaleDto sale;
    private ItemInventoryEntryDto itemInventoryEntry;
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

    public SaleDto getSale() {
        return sale;
    }

    public void setSale(SaleDto sale) {
        this.sale = sale;
    }

    public ItemInventoryEntryDto getItemInventoryEntry() {
        return itemInventoryEntry;
    }

    public void setItemInventoryEntry(ItemInventoryEntryDto itemInventoryEntry) {
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
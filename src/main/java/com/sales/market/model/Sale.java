/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;


import com.sales.market.dto.SaleDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Sale extends ModelBase<SaleDto> {
    @OneToOne()
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    private String description;

    private String image;

    @OneToMany(mappedBy = "sale")
    private List<SaleItemDetail> saleItemDetails;

    public List<SaleItemDetail> getSaleItemDetails() {
        return saleItemDetails;
    }

    public void setSaleItemDetails(List<SaleItemDetail> saleItemDetails) {
        this.saleItemDetails = saleItemDetails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

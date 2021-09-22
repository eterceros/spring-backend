/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item extends ModelBase {
    private String name;
    private String code;
    private Byte[] image;
    @OneToOne(targetEntity = SubCategory.class)
    private SubCategory subCategory;
    @OneToMany(mappedBy = "item")
    private List<ItemInstance> itemInstances;

    public List<ItemInstance> getItemInstances() {
        return itemInstances;
    }

    public void setItemInstances(List<ItemInstance> itemInstances) {
        this.itemInstances = itemInstances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}

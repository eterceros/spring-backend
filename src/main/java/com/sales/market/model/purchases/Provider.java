package com.sales.market.model.purchases;

import com.sales.market.dto.purchaseDto.ProviderDto;
import com.sales.market.model.ModelBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Provider extends ModelBase<ProviderDto> {

    private String name;

    private String code;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL})
    private List<ProviderItem> items;

    public List<ProviderItem> getItems() {
        return items;
    }

    public void setItems(List<ProviderItem> items) {
        this.items = items;
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
}

package com.sales.market.dto.purchaseDto;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.Provider;
import org.modelmapper.ModelMapper;

public class ProviderDto extends DtoBase<Provider> {
    private String name;

    private String code;

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

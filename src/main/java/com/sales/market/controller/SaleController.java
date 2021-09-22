package com.sales.market.controller;

import com.sales.market.dto.SaleDto;
import com.sales.market.model.Sale;
import com.sales.market.service.SaleService;
import com.sales.market.service.GenericService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController extends GenericController<Sale, SaleDto> {
    private SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}

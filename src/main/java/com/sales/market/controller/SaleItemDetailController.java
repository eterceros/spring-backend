package com.sales.market.controller;

import com.sales.market.dto.SaleItemDetailDto;
import com.sales.market.model.SaleItemDetail;
import com.sales.market.service.GenericService;

import com.sales.market.service.SaleItemDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/saleitemdetails")
public class SaleItemDetailController extends GenericController<SaleItemDetail, SaleItemDetailDto> {
    private SaleItemDetailService service;

    public SaleItemDetailController(SaleItemDetailService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}

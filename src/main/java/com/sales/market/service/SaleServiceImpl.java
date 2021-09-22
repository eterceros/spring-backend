package com.sales.market.service;

import com.sales.market.model.Employee;
import com.sales.market.model.Sale;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaleServiceImpl extends GenericServiceImpl<Sale> implements SaleService {
    private final SaleRepository repository;
    private final EmployeeServiceImpl employeeService;

    public SaleServiceImpl(SaleRepository repository, EmployeeServiceImpl employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Override
    protected GenericRepository<Sale> getRepository() {
        return repository;
    }

    @Override
    public Sale save(Sale model) {
        model.setDate(new Date());
        return super.save(model);
    }
}
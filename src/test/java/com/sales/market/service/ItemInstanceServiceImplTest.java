package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInstanceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemInstanceServiceImplTest {
    @Autowired
    ItemInstanceServiceImpl itemInstanceService;
    ItemServiceImpl itemService;

}
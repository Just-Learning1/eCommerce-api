package com.just.learning.ecommerce.service.impl;

import com.just.learning.ecommerce.repository.SqlJdbcRepository;
import com.just.learning.ecommerce.model.Item;
import com.just.learning.ecommerce.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public Item getItem(int id) {
        SqlJdbcRepository sqlJdbcRepository = new SqlJdbcRepository();
        log.info("Retrieving Item");
        return sqlJdbcRepository.getItem(id);
    }
}

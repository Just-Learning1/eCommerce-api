package com.just.learning.ecommerce.controller;

import com.just.learning.ecommerce.model.Item;
import com.just.learning.ecommerce.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/id/{id}")
    public Item getItem(@PathVariable String id) {
        if(ObjectUtils.isEmpty(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input not found");
        }
        return itemService.getItem(Integer.parseInt(id));
    }

}

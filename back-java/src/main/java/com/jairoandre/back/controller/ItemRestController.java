package com.jairoandre.back.controller;


import com.jairoandre.back.entity.ItemEntity;
import com.jairoandre.back.model.ItemPayload;
import com.jairoandre.back.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ItemRestController {

    @Autowired
    private ItemRepository repository;

    @RequestMapping(value = "/api/items", method = {RequestMethod.GET})
    public ResponseEntity<List<ItemEntity>> getAll() {
        List<ItemEntity> array = new ArrayList<>();
        repository.findAll().forEach(array::add);
        return ResponseEntity.ok(array);
    }


    @RequestMapping(value = "/api/items", method = {RequestMethod.POST})
    public ResponseEntity<ItemEntity> postItem(@RequestBody ItemPayload payload) {
        return ResponseEntity.ok(repository.save(ItemEntity.builder()
            .name(payload.getName())
            .detail(payload.getName()).build()));
    }
}

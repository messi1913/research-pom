package com.hirit.research.controller;

import com.hirit.research.food.FoodRetrieveCondition;
import com.hirit.research.food.FoodStoreDTO;
import com.hirit.research.repository.FoodStoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodStoreRepository repository;

    @GetMapping("/foodStores")
    public Page<FoodStoreDTO> getFoodStores(FoodRetrieveCondition condition, Pageable pageable) {
        return repository.retrieveStores(condition, pageable);
    }

    @GetMapping("/foodStoresBetter")
    public Page<FoodStoreDTO> getFoodStoresBetter(FoodRetrieveCondition condition, Pageable pageable) {
        return repository.retrieveStoresBetter(condition, pageable);
    }

}

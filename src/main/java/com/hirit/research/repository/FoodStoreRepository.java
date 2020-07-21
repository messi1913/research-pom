package com.hirit.research.repository;

import com.hirit.research.entity.FoodStore;
import com.hirit.research.food.FoodDAO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodStoreRepository extends JpaRepository<FoodStore, Integer>, FoodDAO {

}

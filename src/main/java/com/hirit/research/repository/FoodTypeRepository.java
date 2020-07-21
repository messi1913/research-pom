package com.hirit.research.repository;


import com.hirit.research.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
}

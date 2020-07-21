package com.hirit.research.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "foodTypeName", "foodOrder"})
public class FoodType {

    @Id
    @GeneratedValue
    @Column(name = "food_type_id")
    private Integer id;

    private String foodTypeName;
    private int foodOrder;

    @OneToMany(mappedBy = "foodType")
    List<FoodStore> foodStoreList = new ArrayList<>();


    public FoodType(String foodTypeName, int foodOrder) {
        this.foodTypeName = foodTypeName;
        this.foodOrder = foodOrder;
    }
}

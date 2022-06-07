package com.hanghae.spring03_test.dto;

import com.hanghae.spring03_test.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FoodDto {
    public Long id;

    public String name;
    public int price;

    public FoodDto(Long id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public FoodDto(Food f) {
        this.id = f.getId();
        this.name = f.getName();
        this.price = f.getPrice();
    }

}

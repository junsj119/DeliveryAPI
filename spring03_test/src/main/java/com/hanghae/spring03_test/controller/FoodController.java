package com.hanghae.spring03_test.controller;

import com.hanghae.spring03_test.domain.Food;
import com.hanghae.spring03_test.dto.FoodDto;
import com.hanghae.spring03_test.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDto){
//        try{
//            foodService.InputFood(restaurantId, requestDto);
//            return "등록에 성공하셨습니다.";
//        }
//        catch(Exception e){
//            return e.getMessage();
//        }
        foodService.InputFood(restaurantId, foodDto);
    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto> getFoods(@PathVariable Long restaurantId){
        return foodService.getFood(restaurantId);
    }
}

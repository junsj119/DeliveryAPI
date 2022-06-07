package com.hanghae.spring03_test.controller;

import com.hanghae.spring03_test.domain.Restaurant;
import com.hanghae.spring03_test.dto.RestaurantDto;
import com.hanghae.spring03_test.repository.RestaurantRepository;
import com.hanghae.spring03_test.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantDto requestDto){
         return restaurantService.createRestaurant(requestDto);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurant();
    }
}

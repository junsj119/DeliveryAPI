package com.hanghae.spring03_test.service;

import com.hanghae.spring03_test.domain.Restaurant;
import com.hanghae.spring03_test.dto.RestaurantDto;
import com.hanghae.spring03_test.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    //음식점 생성
    public Restaurant createRestaurant(RestaurantDto requestDto){
        //입력받은 최소 주문 가격
        int InputMinOrderPrice = requestDto.getMinOrderPrice();
        int InputDeliveryFee = requestDto.getDeliveryFee();
        /*
        최소주문 가격 (minOrderPrice)
            1. 허용값: 1,000원 ~ 100,000원 입력
            2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
            3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
        * */
        if(InputMinOrderPrice < 1000 || InputMinOrderPrice > 100000)
            throw new IllegalArgumentException("최소 주문 가격을 맞춰주세요");
        if(InputMinOrderPrice % 100 != 0)
            throw new IllegalArgumentException("100원단위 이상으로 입력해주세요");
        /*
        기본 배달비 (deliveryFee)
            1. 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
            2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능)
        * */
        if(InputDeliveryFee < 0 || InputDeliveryFee > 10000)
            throw new IllegalArgumentException("배달비 설정을 다시 해주세요");
        if(InputDeliveryFee % 500 != 0)
            throw new IllegalArgumentException("500원단위 이상으로 입력해주세요");

        Restaurant restaurant = new Restaurant(requestDto.getName(), InputMinOrderPrice, InputDeliveryFee);
        return restaurantRepository.save(restaurant);
    }

    //음식점 조회
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }
}

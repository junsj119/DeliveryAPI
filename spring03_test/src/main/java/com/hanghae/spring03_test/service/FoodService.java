package com.hanghae.spring03_test.service;

import com.hanghae.spring03_test.domain.Food;
import com.hanghae.spring03_test.domain.Restaurant;
import com.hanghae.spring03_test.dto.FoodDto;
import com.hanghae.spring03_test.repository.FoodRepository;
import com.hanghae.spring03_test.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    
    //음식 등록
    @Transactional
    public void InputFood(Long restaurantId, List<FoodDto> foodDto){

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new IllegalArgumentException("해당 음식점이 존재하지 않습니다."));


        for (FoodDto requestDto : foodDto) {
            //String InputName = requestDto.getName();
            int InputPrice = requestDto.getPrice();
/*
중복 체크
            Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, InputName);

            if(found.isPresent())
                throw new IllegalArgumentException("중복되었습니다.");
 */
        /*
        1. 허용값: 100원 ~ 1,000,000원
        2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
        3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
        * */
            if(InputPrice < 100 || InputPrice > 1000000)
                throw new IllegalArgumentException("금액 허용 범위를 초과하였습니다.");

            if(InputPrice % 100 != 0)
                throw new IllegalArgumentException("100원 단위로만 입력해주세요.");

            Food food = new Food(restaurant, requestDto);
            foodRepository.save(food);
        }
    }

    //메뉴판 조회
    @Transactional
    public List<FoodDto> getFood(Long restaurantId) {
        List<Food> findFood = foodRepository.findAllByRestaurantId(restaurantId);
        List<FoodDto> foodlist = new ArrayList<>();

        //Dto 리스트에 저장
        for(Food f : findFood){
            foodlist.add(new FoodDto(f));
        }
        return foodlist;
    }
}

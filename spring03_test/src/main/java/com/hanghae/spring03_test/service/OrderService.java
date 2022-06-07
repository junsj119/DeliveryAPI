package com.hanghae.spring03_test.service;

import com.hanghae.spring03_test.domain.Food;
import com.hanghae.spring03_test.domain.Order;
import com.hanghae.spring03_test.domain.OrderFood;
import com.hanghae.spring03_test.domain.Restaurant;
import com.hanghae.spring03_test.dto.OrderDto;
import com.hanghae.spring03_test.dto.OrderFoodDto;
import com.hanghae.spring03_test.dto.OrderResponseDto;
import com.hanghae.spring03_test.repository.FoodRepository;
import com.hanghae.spring03_test.repository.OrderFoodRepository;
import com.hanghae.spring03_test.repository.OrderRepository;
import com.hanghae.spring03_test.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;

    //주문 요청
    @Transactional
    public OrderResponseDto TryOrder(OrderDto requestDto) {        //restaurantId,  list(foodId, quantity)

        //음식점 유무 체크
        Long restaurantId = requestDto.getRestaurantId();
        Restaurant restaurant = CheckRestaurant(restaurantId);

        //클라이언트에서 들어온 데이터들을 담아두는 List
        List<OrderFoodDto> orderFoodList = requestDto.getFoods();
        //foodRequestDto를 저장하는 List
        List<OrderFoodDto>orderFoodLists = new ArrayList<>();

        //주문번호 때문.
        Order order = new Order();
        orderRepository.save(order);

        int totalPrice = restaurant.getDeliveryFee();

        //foodRequestDto -> foodid, quantity        name, price
        for (OrderFoodDto foodRequestDto : orderFoodList) {
            Long foodId = foodRequestDto.getId();
            int quantity = foodRequestDto.getQuantity();

            //수량 체크
            if (quantity < 1 || quantity > 100)
                throw new IllegalArgumentException("수량을 잘못 입력하셨습니다.");

            //음식 유무 체크
            Food food = CheckFood(foodId);

            //이름 저장
            foodRequestDto.setName(food.getName());

            //주문가격
            int orderPrice = quantity * food.getPrice();
            foodRequestDto.setPrice(orderPrice);

            //총 가격
            totalPrice += orderPrice;

            //저장
            orderFoodLists.add(foodRequestDto);

            OrderFood orderFood = new OrderFood(food, quantity, orderPrice);   //orderid, foodid, quantity, price    //저장하려고 하는데 orderId는? deliveryFee는 어떻게 추가?

            //양방향이라 둘 다 저장 느낌?
            orderFood.setOrder(order);
            order.addOrderFoods(orderFood);

            orderFoodRepository.save(orderFood);
        }

        //최소 주문 금액
        if(restaurant.getMinOrderPrice() > totalPrice - restaurant.getDeliveryFee())
            throw new IllegalArgumentException("최소 주문 금액을 맞춰주세요");

        order.setRestaurant(restaurant);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto(order, orderFoodLists);

        return orderResponseDto;
    }


    //체크해주는 친구들
    private Food CheckFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(()
                -> new NullPointerException("음식이 없습니다."));
        return food;
    }

    private Restaurant CheckRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()
                -> new IllegalArgumentException("해당 음식점이 없습니다."));
        return restaurant;
    }




    //주문 조회
    @Transactional
    public List<OrderResponseDto> findAllOrder() {

        //return 해줄 List 선언
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        List<Order> orderList = orderRepository.findAll();

        for(Order order : orderList){
            List<OrderFood> orderFoods = order.getOrderFoods();     //order.getOrderFoods().strean()

/*
            List<OrderFoodDto> orderFoodDtoList2 = new ArrayList<>();
            for (OrderFood orderFood : orderFoods) {
                OrderFoodDto orderFoodDto = new OrderFoodDto(orderFood.getId(), orderFood.getFood().getName(), orderFood.getQuantity(), orderFood.getPrice());
                //orderFoodDto.setName(orderFood.getFood().getName());
                orderFoodDtoList2.add(orderFoodDto);
            }
 */
            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            List<OrderFoodDto> orderFoodDtoList = orderFoods.stream()
                    .map((o) -> new OrderFoodDto(o.getId(), o.getFood().getName(), o.getQuantity(), o.getPrice()))
                    .collect(toList());

            OrderResponseDto ordersResponseDto = new OrderResponseDto(order, orderFoodDtoList); //orderFoodDto 넣어야 한다.
            orderResponseDtoList.add(ordersResponseDto);
        }


        return orderResponseDtoList;
    }
}
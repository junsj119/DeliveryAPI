package com.hanghae.spring03_test.controller;

import com.hanghae.spring03_test.domain.Order;
import com.hanghae.spring03_test.dto.OrderDto;
import com.hanghae.spring03_test.dto.OrderResponseDto;
import com.hanghae.spring03_test.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    //주문
    @PostMapping("/order/request")
    public OrderResponseDto requestOrder(@RequestBody OrderDto requestDto){
        return orderService.TryOrder(requestDto);
    }

    //주문조회
    @GetMapping("/orders")
    public List<OrderResponseDto> findOrder() {
        return orderService.findAllOrder();
    }
}

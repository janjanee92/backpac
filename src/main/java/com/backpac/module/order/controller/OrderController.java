package com.backpac.module.order.controller;

import com.backpac.module.order.dto.OrderDto;
import com.backpac.module.order.service.OrderService;
import com.backpac.module.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 단일 회원 주문 목록 조회
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<List<OrderDto>> findOrdersByMember(@PathVariable Long id) {
        List<Order> orders = orderService.findOrdersByMember(id);
        return new ResponseEntity<>(
                orders.stream()
                .map(OrderDto::entityToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}

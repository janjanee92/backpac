package com.backpac.module.order.controller;

import com.backpac.module.order.dto.OrderDto;
import com.backpac.module.order.service.OrderService;
import com.backpac.module.order.entity.Order;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/member/{id}")
    @ApiOperation(value = "단일 회원 주문 목록 조회", notes="회원 id를 입력받아 단일 회원의 주문 목록을 조회합니다.")
    @ApiImplicitParam(name = "id", paramType = "path", value = "회원 id (ex - 1)")
    public ResponseEntity<List<OrderDto>> findOrdersByMember(@PathVariable Long id) {
        List<Order> orders = orderService.findOrdersByMember(id);
        return new ResponseEntity<>(
                orders.stream()
                .map(OrderDto::entityToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}

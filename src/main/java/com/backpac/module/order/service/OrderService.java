package com.backpac.module.order.service;

import com.backpac.module.order.entity.Order;
import com.backpac.module.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findOrdersByMember(Long id) {
        return orderRepository.findAllByMember(id);
    }
}

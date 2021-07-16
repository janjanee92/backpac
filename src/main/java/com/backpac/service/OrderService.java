package com.backpac.service;

import com.backpac.domain.Order;
import com.backpac.repository.order.OrderRepository;
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

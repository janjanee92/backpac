package com.backpac.service;

import com.backpac.domain.Member;
import com.backpac.domain.Order;
import com.backpac.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberService memberService;
    private final OrderRepository orderRepository;

    public List<Order> findOrdersByMember(Long id) {
        Member member = memberService.findOne(id);
        return orderRepository.findAllByMember(member);
    }
}

package com.backpac.repository;

import com.backpac.domain.Member;
import com.backpac.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByMember(Member member);
}

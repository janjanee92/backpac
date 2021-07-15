package com.backpac.repository;

import com.backpac.domain.Member;
import com.backpac.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join o.member m where m.id = :id")
    List<Order> findAllByMember(@Param("id") Long age);

}

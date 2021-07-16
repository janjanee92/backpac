package com.backpac.repository.order;

import com.backpac.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join o.member m where m.id = :id")
    List<Order> findAllByMember(@Param("id") Long age);

}

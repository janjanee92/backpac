package com.backpac.dto;

import com.backpac.domain.Gender;
import com.backpac.domain.Member;
import com.backpac.domain.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberOrderDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Long lastOrderId;
    private String lastOrderNo;
    private String productName;
    private LocalDateTime paymentTime;

    public MemberOrderDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.gender = member.getGender();
        this.phoneNumber = member.getPhoneNumber();
        setLastOrder(member.getOrders());
    }

    private void setLastOrder(List<Order> orders) {
        if (orders.size() > 0) {
            Order order = orders.get(0);
            this.lastOrderId = order.getId();
            this.lastOrderNo = order.getOrderNo();
            this.productName = order.getProductName();
            this.paymentTime = order.getPaymentTime();
        }
    }
}

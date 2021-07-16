package com.backpac.dto;

import com.backpac.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private Long id;
    private String orderNo;
    private String productName;
    private LocalDateTime paymentTime;

    public static OrderDto entityToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderNo(order.getOrderNo())
                .productName(order.getProductName())
                .paymentTime(order.getPaymentTime())
                .build();
    }
}

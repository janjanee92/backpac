package com.backpac.dto;

import com.backpac.domain.Gender;
import com.backpac.domain.Member;
import com.backpac.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDto {
    private Long id;
    private String orderNo;
    private String productName;
    private LocalDateTime paymentTime;
    private Long memberd;

    public static OrderResponseDto entityToDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .orderNo(order.getOrderNo())
                .productName(order.getProductName())
                .paymentTime(order.getPaymentTime())
                .memberd(order.getMember().getId())
                .build();
    }
}

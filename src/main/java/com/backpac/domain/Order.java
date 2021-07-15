package com.backpac.domain;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.text.RandomStringGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Table(name="backpac_order")
@ToString(exclude = {"member"})
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(length = 12, nullable = false, unique = true)
    private String orderNo;

    @Column(length = 100, nullable = false)
    private String productName;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void createOrderNo (LocalDateTime paymentTime) {
        char [][] pairs = {{'A','Z'},{'0','9'}};
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange(pairs)
                .build();
        String randomLetters = generator.generate(6);
        String formatDate = paymentTime.format(DateTimeFormatter.ofPattern("yyMMdd"));
        this.orderNo = formatDate + randomLetters;
    }

    public static Order createOrder(String productName, Member member) {
        LocalDateTime paymentTime = LocalDateTime.now();
        Order order = new Order();
        order.createOrderNo(paymentTime);
        order.productName = productName;
        order.paymentTime = paymentTime;
        order.setMember(member);
        return order;
    }
}

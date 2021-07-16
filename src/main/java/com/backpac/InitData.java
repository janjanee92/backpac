package com.backpac;

import com.backpac.domain.Gender;
import com.backpac.domain.Member;
import com.backpac.domain.Order;
import com.backpac.repository.member.MemberRepository;
import com.backpac.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InitData {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void memberInit() {
        List<Member> members = new ArrayList<>();

        members.add(Member.createMember("이춘식", "bomchun","chun2@gmail.com",
                "Chun!23456", "01054547676", Gender.F));
        members.add(Member.createMember("라이언", "ryan", "ryan@naver.com",
                "Ryan!23456", "01034341212", Gender.M));
        members.add(Member.createMember("이짱구", "zzanggu", "zzanggu@gmail.com",
                "Zzang!23456", "01011112312", Gender.M));
        members.add(Member.createMember("단무지", "danmuzi", "danmuzi@gmail.com",
                            "Danmuzi!23456", "01045679392", Gender.M));
        members.add(Member.createMember("최고심", "choigo", "choigo@gmail.com",
                "Choigo!23456", "01011412312", Gender.F));
        members.add(Member.createMember("박둘리", "parkdooli", "parkdooli@gmail.com",
                "Parkdooli!23456", "01075238323", Gender.F));
        members.add(Member.createMember("도라에몽", "doraemon", "doraemon@gmail.com",
                "Doraemon!23456", "01063237452", Gender.M));
        members.add(Member.createMember("스폰지밥", "spongebob", "spongebob@gmail.com",
                "Spongebob!23456", "01096324123", Gender.M));
        members.add(Member.createMember("피카츄", "picachu", "picachu@gmail.com",
                "Picachu!23456", "01023491112", Gender.F));
        members.add(Member.createMember("김뿌까", "ppucca", "ppucca@gmail.com",
                "Ppucca!23456", "01011234598", Gender.M));
        members.add(Member.createMember("왕쿠키", "bigcookie", "bigcookie@gmail.com",
                "Bigcookie!23456", "01099992234", Gender.M));
        members.add(Member.createMember("이두모", "dumolee", "dumolee@gmail.com",
                "Dumolee!23456", "01062341112", Gender.M));


        members.forEach(memberRepository::save);

        List<Order> orders = new ArrayList<>();
        orders.add(Order.createOrder("목걸이", members.get(0)));
        orders.add(Order.createOrder("반지", members.get(0)));
        orders.add(Order.createOrder("지갑", members.get(0)));
        orders.add(Order.createOrder("목걸이", members.get(1)));
        orders.add(Order.createOrder("반지", members.get(1)));
        orders.add(Order.createOrder("핸드폰 케이스", members.get(2)));
        orders.add(Order.createOrder("스티커", members.get(2)));
        orders.add(Order.createOrder("마크라메", members.get(3)));
        orders.add(Order.createOrder("키링", members.get(4)));
        orders.add(Order.createOrder("책갈피", members.get(5)));
        orders.add(Order.createOrder("명함", members.get(6)));
        orders.add(Order.createOrder("컵홀더", members.get(7)));

        orders.forEach(orderRepository::save);

    }
}


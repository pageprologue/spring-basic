package spring.basic.core;

import spring.basic.core.member.Grade;
import spring.basic.core.member.Member;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;
import spring.basic.core.order.Order;
import spring.basic.core.order.OrderService;
import spring.basic.core.order.OrderServiceImpl;

import java.util.Arrays;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
        AppConfig app = new AppConfig();
        MemberService memberService = app.memberService();
        OrderService orderService = app.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}

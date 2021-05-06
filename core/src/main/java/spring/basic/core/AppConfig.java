package spring.basic.core;

import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;
import spring.basic.core.member.MemoryMemberRepository;
import spring.basic.core.order.OrderService;
import spring.basic.core.order.OrderServiceImpl;

public class AppConfig {

    /**
     * 실제 구현 객체 생성
     * - 생성자를 통해 주입하는 방식
     */
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}

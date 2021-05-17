package spring.basic.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.basic.core.discount.DiscountPolicy;
import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.discount.RateDiscountPolicy;
import spring.basic.core.member.MemberRepository;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;
import spring.basic.core.member.MemoryMemberRepository;
import spring.basic.core.order.OrderService;
import spring.basic.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    /**
     * 실제 구현 객체 생성
     * - 생성자를 통해 주입하는 방식
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

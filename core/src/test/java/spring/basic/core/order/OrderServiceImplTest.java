package spring.basic.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.discount.RateDiscountPolicy;
import spring.basic.core.member.Grade;
import spring.basic.core.member.Member;
import spring.basic.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {

    @Test
    void crateOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "member1", Grade.VIP));

        OrderServiceImpl fixOrderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        OrderServiceImpl rateOrderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        Order fixOorder = fixOrderService.createOrder(1L, "itemA", 20000);
        Order rateOorder = rateOrderService.createOrder(1L, "itemA", 20000);

        Assertions.assertThat(fixOorder.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(rateOorder.getDiscountPrice()).isEqualTo(2000);
    }
}

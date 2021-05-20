package spring.basic.core.order;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.basic.core.AppConfig;
import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.member.*;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig app = new AppConfig();
        memberService = app.memberService();
        orderService = app.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    /*
    // new 로 생성하는 객체는 스프링 컨테이너가 관리하지 않게 되므로 테스트 코드에서 사용하기 어렵게 된다.
    // 필드 주입으로 하게 되면, setter를 만들어줘야 한다.
    @Test
    void findInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();

        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDisCountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1l, "test", 1000);
    }
    */
}

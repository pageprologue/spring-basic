package spring.basic.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.basic.core.AppConfig;
import spring.basic.core.member.MemberRepository;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;
import spring.basic.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("AppConfig 에서 @Bean 으로 등록된 메소드를 실행하면서 new 로 객체를 생성하게 될 때 싱글톤이 유지 되는지 테스트")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberService = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
        MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();

        System.out.println("memberService -> memberRepository " + memberRepository1);
        System.out.println("orderService -> memberRepository " + memberRepository2);
        System.out.println("memberService -> memberRepository " + memberService);

        Assertions.assertThat(memberRepository1).isSameAs(memberService);
        Assertions.assertThat(memberRepository2).isSameAs(memberService);
    }
    
    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // class spring.basic.core.AppConfig$$EnhancerBySpringCGLIB$$84df1d99
        // @Configuration 을 주석처리하면 class spring.basic.core.AppConfig 이 나오고, memberRepository 가 new 로 여러번 생성된다.
        System.out.println("bean = " + bean.getClass());
    }
}

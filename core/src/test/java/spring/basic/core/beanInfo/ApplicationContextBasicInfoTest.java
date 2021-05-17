package spring.basic.core.beanInfo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.basic.core.AppConfig;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현 타입으로 조회")
    void findBeanByImplType() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("조회할 수 없는 빈")
    void notFoundBeanName() {
//        MemberService memberService = ac.getBean("test", MemberServiceImpl.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("test", MemberServiceImpl.class));
    }

}

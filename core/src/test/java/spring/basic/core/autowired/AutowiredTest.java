package spring.basic.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import spring.basic.core.member.Member;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1 (Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("noBean2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("noBean3 = " + member);
        }
    }
}

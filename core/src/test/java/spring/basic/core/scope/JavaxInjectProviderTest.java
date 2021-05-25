package spring.basic.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class JavaxInjectProviderTest {

    @Test
    void javaxInjectProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(InjectBean.class, PrototypeBean.class);
        InjectBean injectBean1 = ac.getBean(InjectBean.class);
        int count1 = injectBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        InjectBean injectBean2 = ac.getBean(InjectBean.class);
        int count2 = injectBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    static class InjectBean {
        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
}

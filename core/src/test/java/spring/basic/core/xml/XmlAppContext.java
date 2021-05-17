package spring.basic.core.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.basic.core.member.MemberService;
import spring.basic.core.member.MemberServiceImpl;

public class XmlAppContext {

    @Test
    void xmlAppContext () {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

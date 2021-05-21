package spring.basic.core.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.basic.core.annotation.MainDiscountPolicy;
import spring.basic.core.member.Grade;
import spring.basic.core.member.Member;

@Primary
@Component
//@MainDiscountPolicy
//@Qualifier("rateDiscount")
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

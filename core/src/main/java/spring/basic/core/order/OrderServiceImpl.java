package spring.basic.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.basic.core.discount.DiscountPolicy;
import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.discount.RateDiscountPolicy;
import spring.basic.core.member.Member;
import spring.basic.core.member.MemberRepository;
import spring.basic.core.member.MemoryMemberRepository;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // @Autowired - 필드 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    // 생성자 주입 - @RequiredArgsConstructor 로 대체 가능
     @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("rateDiscount") DiscountPolicy discountPolicy) {
        System.out.println("생성자 주입");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    /*
    // 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("메서드 주입");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    /*
    // 수정자 주입
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("수정자 주입 : memberRepository");
        this.memberRepository = memberRepository;
    }

    // 수정자 주입
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("수정자 주입 : discountPolicy");
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

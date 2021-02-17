package spring.basic.core.order;

import spring.basic.core.discount.DiscountPolicy;
import spring.basic.core.discount.FixDiscountPolicy;
import spring.basic.core.member.Member;
import spring.basic.core.member.MemberRepository;
import spring.basic.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

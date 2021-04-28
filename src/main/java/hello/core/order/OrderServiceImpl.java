package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // RequiredArgs인 final붙은 필드를 모아서 자동으로 생성자를 만들어 줌
public class OrderServiceImpl implements OrderService {

   // 생성자 주입 전 코드 = 구현체에 의존한다
   // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // Field Injection is not recommended
    // @Autowired private  MemberRepository memberRepository;
    // @Autowired private  DiscountPolicy discountPolicy;

    // 생성자 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자(setter) 주입 법
    // @Autowired
    // public void setMemberRepository(MemberRepository memberRepository) {
    //     this.memberRepository = memberRepository;
    // }
    // @Autowired
    // public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //     this.discountPolicy = discountPolicy;
    // }

    // 롬복의 RequiredArgs를 통해 만들어지므로 주석 처리 함
    // 생성자 주입을 통해 DIP를 지키는 코드
    // @Autowired // 생성자가 하나면 Autowired 생략 가능
    // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // SRP(단일책임원식)를 잘 지킨 예시 Single Responsibility Principle
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

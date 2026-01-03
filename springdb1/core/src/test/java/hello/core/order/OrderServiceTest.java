package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy; // AppConfig에서 사용한 구현체
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach; // import
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    // MemberService memberService = new MemberServiceImpl(); // 👈 (X) 삭제
    // OrderService orderService = new OrderServiceImpl(); // 👈 (X) 삭제

    MemberService memberService;
    OrderService orderService;

    @BeforeEach // 각 테스트 실행 전에 먼저 실행되는 부분
    public void beforeEach() {
        // AppConfig에서 하던 방식(의존관계 주입)을 테스트 코드에서 수동으로 해줍니다.

        // 1. AppConfig의 memberRepository() 역할
        MemberRepository memberRepository = new MemoryMemberRepository();

        // 2. AppConfig의 discountPolicy() 역할
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        // 3. AppConfig의 memberService() 역할 (의존성 주입)
        memberService = new MemberServiceImpl(memberRepository);

        // 4. AppConfig의 orderService() 역할 (의존성 주입)
        orderService = new OrderServiceImpl(memberRepository, discountPolicy);
    }


    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // ❗ join을 위한 memberService는 @BeforeEach에서 이미 생성되었습니다.
        // ❗ 하지만 join을 하려면 memberRepository에 회원이 저장되어야 합니다.
        // ❗ MemberServiceImpl은 memberRepository를 사용하므로,
        // ❗ memberService.join(member);를 호출하면 됩니다.

        // (수정) : join을 위해 memberRepository가 아닌 memberService를 사용해야 합니다.
        // memberRepository.save(member); // 👈 (이전 오류의 원인)
        memberService.join(member); // 👈 이렇게 수정


        // orderService는 @BeforeEach에서 이미 생성되었습니다.
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
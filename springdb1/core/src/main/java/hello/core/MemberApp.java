package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // ApplicationContext: 스프링 컨테이
        // AnnotationConfigApplicationContext: @Configuration이 붙은 AppConfig.class를 설정 정보로 사용
        ApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);

        // 컨테이너에서 이름이 "memberService"이고 타입이 MemberService.class인 빈을 찾아서 반환
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable; // 여기가 org.springframework... 여야 합니다.

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        // TestBean.class가 아래에 정의한 static class TestBean을 가리켜야 합니다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 1. @Autowired(required=false): 자동 주입 대상이 없으면 수정자 메서드 자체가 호출 안 됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 2. @Nullable: 자동 주입 대상이 없으면 null이 입력됨
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 3. Optional<>: 자동 주입 대상이 없으면 Optional.empty가 입력됨
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
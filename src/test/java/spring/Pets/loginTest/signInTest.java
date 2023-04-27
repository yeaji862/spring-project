package spring.Pets.loginTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.Pets.member.LoginType;
import spring.Pets.member.Member;
import spring.Pets.member.MemberService;

@SpringBootTest
public class signInTest {

    @Autowired
    MemberService service;

    @Test
    void userImg_null_signInTest(){
        Member member = new Member();
        member.setUser_id("test");
        member.setUser_pass("1111");
        member.setUser_name("test");
        member.setAnimal_type("test");
        member.setAnimal_birth("2020.01.01");
        member.setAnimal_gender("test");
        member.setLogin_type(LoginType.GOOGLE);
        service.join(member);
    }
}

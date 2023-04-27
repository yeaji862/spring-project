package spring.Pets.securityConfig.auth;

import spring.Pets.member.Member;
import spring.Pets.memberImpl.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 .loginProcessingUrl("/login_proc")
// login_proc 요청이오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper mapper;
    //시큐리티 session (내부 Authentication (내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException { // 매개변수명은 input타입의 id와 동일 시켜야 한다
        System.out.println(user_id);
            Member userEntity = mapper.login(user_id);
            if(userEntity == null){
                throw new UsernameNotFoundException("User not found with username: " + user_id);
            }
            return new PrincipalDetails(userEntity);
    }
}

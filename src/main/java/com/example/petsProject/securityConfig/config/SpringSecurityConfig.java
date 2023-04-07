package com.example.petsProject.securityConfig.config;

import com.example.petsProject.securityConfig.auth.PrincipalDetailsService;
import com.example.petsProject.securityConfig.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

//    @Autowired
//    private UserDetailsService userDetailsService;
// 로그인 성공전 url로 들어가기전에 제한이 되는데 로그인 성공하면 검색한 url로 바로 이동이 가능하다
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증이 필요한 주소
                .anyRequest().permitAll() // 위의 주소를 제외한 나머지는 인증이 없어도 접근가능
                .and()
                .formLogin()
                .loginPage("/") // 로그인 페이지를 지정했을 때에는 인증이 필요한 주소로 들어갈 때 해당 로그인 페이지가 나타난다
                .usernameParameter("user_id")
                .passwordParameter("user_pass")
                .loginProcessingUrl("/login_proc") // /login_proc 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다 (컨트롤러를 따로 만들지 않아도 된다)
                .defaultSuccessUrl("/user/success")
                .failureForwardUrl("/fail")
                .and()
                .oauth2Login() // 소셜로그인 시큐리티
                .loginPage("/")
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .defaultSuccessUrl("/user/oauthSuccess")
                .and()
                .rememberMe()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder EncoderPW(){ // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService);
    }



}

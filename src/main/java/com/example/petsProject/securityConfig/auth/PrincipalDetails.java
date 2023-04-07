package com.example.petsProject.securityConfig.auth;

//시큐리티가 주소 요청이 오면 url 을 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 되면 session 을 만들어줍니다
//시큐리티 세션과 일반 세션을 차이가 있다
//Security ContextHolder 에 저장된다
//오브젝트 => Authentication 타입 객체
//Authentication 안에 유저 정보가 있어야 됨
//유저 오브젝트의 타입 => UserDetails 타입
//Security session => Authentication => UserDetails(PrincipalDetails) 타입이여야한다

import com.example.petsProject.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails{
    private Member member; // 콤포지션


    //일반 로그인 생성자
    public PrincipalDetails(Member member) {
            this.member = member;
    }
    //oauth 로그인 생성자

    //해당 User 의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(member.getLogin_type());
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getUser_pass();
    }

    @Override
    public String getUsername() {
        return member.getUser_id();
    }

    public String getUser_name(){
        return member.getUser_name();
    }

    public String getUser_img_file(){
        return member.getUser_img_file();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정 활성화 여부
    public boolean isEnabled() {
        return true;
    }

}

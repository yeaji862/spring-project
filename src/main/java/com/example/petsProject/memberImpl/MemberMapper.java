package com.example.petsProject.memberImpl;

import com.example.petsProject.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    Member selectName(String user_id); // 유저 닉네임 검색

    String nameCheck(String user_name); // 닉네임 중복 검색

    int joinMapper(Member member); // 회원가입

    String findID(String user_phone); // ID 찾기

    String findPass(Member member); // 회원 정보 찾기

    String overlapId(String user_id); // 중복 ID 찾기

    int editInfo(Member member); // 프로필 수정

    Member login(String user_id); // 로그인

    Member selectProfile(String user_name); // 프로필 검색

    int editPass(Member member); // 비밀번호 변경

    List<Member> idSearchPost(String search); // 닉네임 기반 검색


}

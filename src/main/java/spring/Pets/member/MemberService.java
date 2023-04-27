package spring.Pets.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface MemberService {

    Member selectName(String user_id); // 유저 닉네임 검색

    String nameCheck(String user_name); // 닉네임 중복 검색

    int join(Member member); // 회원가입

    String findId(String user_phone); // ID 찾기

    String findPass(Member member); // 회원 정보 찾기

    int editPass(Member member); // PassWord 수정

    String overlapId(String user_id); // 중복 ID 확인

    int editInfo(Member member); // 프로필 수정

    Member login(String user_id); // 로그인

    Member selectProfile(String user_name); // 프로필 검색

    List<Member> idSearchPost(String search); // 닉네임 기반 검색

    public void fileSave(MultipartFile file , String user_id);

    int userFollowUpdate(String user_name);

    int userFollowDelete(String user_name);

    int userFollowingUpdate(String user_name);

    int userFollowingDelete(String user_name);


}

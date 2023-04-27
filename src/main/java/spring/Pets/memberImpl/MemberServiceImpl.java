package spring.Pets.memberImpl;

import spring.Pets.member.Member;
import spring.Pets.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("MemberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    MemberMapper mapper;

    @Override
    public Member selectName(String user_id) {
        return mapper.selectName(user_id);
    }

    @Override
    public String nameCheck(String user_name) {
        return mapper.nameCheck(user_name);
    }

    @Override
    public int join(Member member) {
        return mapper.joinMapper(member);
    }

    @Override
    public String findId(String user_phone) {
        return mapper.findID(user_phone);
    }

    @Override
    public String findPass(Member member) {
        return mapper.findPass(member);
    }

    @Override
    public int editPass(Member member) {
        return mapper.editPass(member);
    }

    @Override
    public String overlapId(String user_id) {
        return mapper.overlapId(user_id);
    }

    @Override
    public int editInfo(Member member) {
        return mapper.editInfo(member);
    }

    @Override
    public Member login(String user_id) {
        return mapper.login(user_id);
    }

    @Override
    public Member selectProfile(String user_name) {
        return mapper.selectProfile(user_name);
    }

    @Override
    public List<Member> idSearchPost(String search) {
        return mapper.idSearchPost(search);
    }
    @Override
    public void fileSave(MultipartFile file , String user_id){
        try{
            if(!file.isEmpty()){
                String fullPath = uploadPath + "/profile/" + user_id +"_profile_img.jpg";
                File fileDelete = new File(fullPath);
                boolean result = fileDelete.delete();
                file.transferTo(new File(fullPath));
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public int userFollowUpdate(String user_name) {
        return mapper.userFollowUpdate(user_name);
    }

    @Override
    public int userFollowDelete(String user_name) {
        return mapper.userFollowDelete(user_name);
    }

    @Override
    public int userFollowingUpdate(String user_name) {
        return mapper.userFollowingUpdate(user_name);
    }

    @Override
    public int userFollowingDelete(String user_name) {
        return mapper.userFollowingDelete(user_name);
    }


}

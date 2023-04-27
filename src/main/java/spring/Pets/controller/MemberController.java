package spring.Pets.controller;

import org.springframework.beans.factory.annotation.Value;
import spring.Pets.CoolSMS.CertificationNumber;
import spring.Pets.CoolSMS.CertificationNumberImpl;
import spring.Pets.member.Member;
import spring.Pets.member.MemberService;
import spring.Pets.securityConfig.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
public class MemberController {

    @Autowired
    MemberService service;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${coolSMS_key}")
    private String coolSMS_key;
    @Value("${coolSMS_secret}")
    private String coolSMS_secret;

    private void setSessionId(HttpServletRequest request , String user_id){ // 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);
    }

    private void setSessionName(HttpServletRequest request , String user_name){ // 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("user_name", user_name);
    }

    private void setSessionUser_img_file(HttpServletRequest request , String user_img_file){ // 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("user_img_file", user_img_file);
    }

    @RequestMapping(value = "/user/success") // 로그인 성공
    public String success(Authentication authentication , HttpServletRequest request) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        setSessionId(request , principalDetails.getUsername());
        setSessionName(request , principalDetails.getUser_name());
        setSessionUser_img_file(request , principalDetails.getUser_img_file());
        return "redirect:/user/allPost";
    }

    @RequestMapping(value = "/user/oauthSuccess") // 소셜 로그인 성공
    public String oauthSuccess(Authentication authentication , HttpServletRequest request) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Member member = service.selectName((String) "google_" + oAuth2User.getAttributes().get("sub"));
        setSessionId(request , (String) "google_" + oAuth2User.getAttributes().get("sub"));
        setSessionName(request ,  member.getUser_name());
        setSessionUser_img_file(request , member.getUser_img_file());
        return "redirect:/user/allPost";
    }

    @RequestMapping(value = "/googleJoinMove.mem") // 구글회원 정보 추가 입력 페이지 이동
    public String googleJoinMove(Authentication authentication ,  Model model) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String oauthUser_id = oAuth2User.getAttribute("sub");
        String user_id = "google_" + oauthUser_id;
        model.addAttribute("user_id", user_id);
        return "member/googleJoin";
    }


    @RequestMapping(value = "/findPass.mem")
    public String findPass(Member member, Model model) { // 비밀번호 찾기 상세
        model.addAttribute("user_id" , member.getUser_id());
        return "member/findPassCheck";
    }

    @ResponseBody
    @RequestMapping(value = "/findPassCheck.mem")
    public String findPassCheck(String user_id , String user_phone) {
        Member member = new Member();
        member.setUser_id(user_id);
        member.setUser_phone(user_phone);
        String userId = service.overlapId(user_id);
        if(userId != null){
            String userIdPhone = service.findPass(member);
            if (userIdPhone != null) {
                return "true";
            } else {
                return "아이디와 전화번호가 일치하지 않습니다";
            }
        }else{
            return "아이디가 존재하지 않습니다";
        }
    }

    @RequestMapping(value = "/fail")
    public String failTest(Model model){ // 로그인 실패시
            model.addAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다");
            model.addAttribute("url", "/");
            return "alert";
    }

    @PostMapping(value = "/join.mem")
    public String join(Member member, Model model , HttpServletRequest request , @RequestParam("user_name_img_file") MultipartFile file) { // 회원가입 완료 된 후 메인 페이지로 이동
        String user_pass = member.getUser_pass();
        member.setUser_pass(bCryptPasswordEncoder.encode(member.getUser_pass()));
        if(file.isEmpty()){
            member.setUser_img_file("basic/paw.png");
        }else{
            member.setUser_img_file(member.getUser_id() +"_profile_img.jpg");
        }
        int join = service.join(member);
        if (join > 0) {
            service.fileSave(file , member.getUser_id());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(member.getUser_id(), user_pass);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            RememberMeServices rememberMeServices = new TokenBasedRememberMeServices("my-remember-me-key", userDetailsService);
            rememberMeServices.loginSuccess(request, null, authentication);
            setSessionId(request , member.getUser_id());
            setSessionName(request , member.getUser_name());
            setSessionUser_img_file(request , member.getUser_img_file());
            model.addAttribute("msg", "Pets 가입을 환영합니다!");
            model.addAttribute("url", "/user/allPost");
        } else {
            model.addAttribute("msg", "회원가입이 정상적으로 진행되지 않았습니다");
            model.addAttribute("url", "/");
        }

        return "alert";
    }


    @RequestMapping(value = "/user/editProfile")
    public String editProfile(Member member, Model model , HttpServletRequest request , @RequestParam("user_name_img_file") MultipartFile file) throws UnsupportedEncodingException { // 프로필 변경
        member.setUser_id((String) request.getSession().getAttribute("user_id"));
        service.fileSave(file , (String) request.getSession().getAttribute("user_id"));
        member.setUser_img_file((String) request.getSession().getAttribute("user_id")+"_profile_img.jpg");
        service.editInfo(member);
        String encodedValue = URLEncoder.encode(member.getUser_name(), "UTF-8");
        setSessionName(request , member.getUser_name());
        return "redirect:/user/profilePost?pass=" + encodedValue;
    }

    @RequestMapping(value = "/editPass.mem")
    public String editPass(Member member , Model model){
        System.out.println(member.toString());
        member.setUser_pass(bCryptPasswordEncoder.encode(member.getUser_pass()));
        service.editPass(member);
        model.addAttribute("msg", "비밀번호가 정상적으로 변경되었습니다");
        model.addAttribute("url", "/");
        return "alert";
    }


    @ResponseBody
    @RequestMapping(value = "/user_id_check.mem")
    public String user_id_check(String user_id) {
        String findId = service.overlapId(user_id);
        if(findId != null){
            return "true";
        }else{
            return "false";

        }
    }

    @ResponseBody
    @RequestMapping(value = "/authenticationNumber.mem") // 인증번호 보내기
    public String authenticationNumber(String user_phone , String randomNumber) {
        CertificationNumber certificationNumber = new CertificationNumberImpl(coolSMS_key , coolSMS_secret);
            return certificationNumber.sendOne(user_phone , randomNumber);
        }

    @ResponseBody
    @RequestMapping(value = "/user/sessionName")
    public String sessionName(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("user_name");
    }

    @ResponseBody
    @RequestMapping(value = "/nameCheck.mem")
    public String nameCheck(String user_name) {
       String name = service.nameCheck(user_name);
       if(name == null){
           return "false";
       }else{
           return "true";
       }
    }


}

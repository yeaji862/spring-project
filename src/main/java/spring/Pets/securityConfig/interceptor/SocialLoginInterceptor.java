package spring.Pets.securityConfig.interceptor;

import spring.Pets.memberImpl.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class SocialLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private final MemberMapper mapper;

    public SocialLoginInterceptor(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String googleID = (String) attributes.get("sub");
            String user_id = "google_" + googleID;
            String userIdEntity = mapper.overlapId(user_id);
            System.out.println(userIdEntity);
            if (userIdEntity == null) {
                response.sendRedirect("/googleJoinMove.mem");
                return false;
            }
        }
        return true;
    }

}

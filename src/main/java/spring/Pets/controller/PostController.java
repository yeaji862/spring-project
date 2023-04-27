package spring.Pets.controller;

import spring.Pets.community.CommunityService;
import spring.Pets.community.Follow;
import spring.Pets.member.Member;
import spring.Pets.member.MemberService;
import spring.Pets.post.Post;
import spring.Pets.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class PostController {

    @Autowired
    PostService service;

    @Autowired
    MemberService memberService;

    @Autowired
    CommunityService communityService;


    @RequestMapping(value = "/user/allPost")
    public String allPost(Model model , HttpServletRequest request){
        model.addAttribute("post" , service.allPost());
        model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postIndex";
    }

    @RequestMapping(value = "/user/search")
    public String search(Model model , @RequestParam(value = "search") String search , HttpServletRequest request){
        model.addAttribute("post" , service.conSearchPost(search));
        model.addAttribute("member" , memberService.idSearchPost(search));
        model.addAttribute("search" , "search");
        model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/allSearch";
    }

    @RequestMapping(value = "/user/userPost")
    public String userPost(Model model , HttpServletRequest request){
        String user_id = (String) request.getSession().getAttribute("user_id");
        String user_name = (String) request.getSession().getAttribute("user_name");
        model.addAttribute("post" , service.profilePost(user_name));
        model.addAttribute("member" , memberService.login(user_id));
        model.addAttribute("myProfile" , "myProfile");
        model.addAttribute("followerList" , communityService.followerList(user_name));
        model.addAttribute("followingList" , communityService.followingList(user_name));
        model.addAttribute("user_name" , user_name);
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postDetail";
    }

    @RequestMapping(value = "/user/profilePost")
    public String profilePost(Model model , @RequestParam(value = "pass") String user_name , HttpServletRequest request){
        Member member = memberService.selectProfile(user_name);
        try {
            model.addAttribute("post" , service.profilePost(user_name));
            model.addAttribute("member" , member);
            Follow follow = Follow.followMapping((String) request.getSession().getAttribute("user_id") , (String) request.getSession().getAttribute("user_name")
                    , user_name);
            model.addAttribute("check" , communityService.selectFollow(follow));
            model.addAttribute("followerList" , communityService.followerList(user_name));
            model.addAttribute("followingList" , communityService.followingList(user_name));
            model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
            model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
            if(member.getUser_name().equals((String) request.getSession().getAttribute("user_name"))){
                model.addAttribute("myProfile" , "myProfile");
            }
        }catch (NullPointerException e){
            String referer = request.getHeader("referer");
            return "redirect:" + referer;
        }
        return "post/postDetail";
    }

    @RequestMapping(value = "/user/newestPost")
    public String newestPost(Model model , HttpServletRequest request){
        model.addAttribute("newestPost" , "newestPost");
        model.addAttribute("post" , service.newestPost());
        model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postIndex";
    }

    @RequestMapping(value = "/user/popularityPost")
    public String popularityPost(Model model , HttpServletRequest request){
        model.addAttribute("popularityPost" , "popularityPost");
        model.addAttribute("post" , service.popularityPost());
        model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postIndex";
    }

    @RequestMapping(value = "/user/likePost")
    public String likePost(Model model , HttpServletRequest request){
        model.addAttribute("likePost" , "likePost");
        model.addAttribute("post" , service.likePost((String) request.getSession().getAttribute("user_name")));
        model.addAttribute("user_name" , (String) request.getSession().getAttribute("user_name"));
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postIndex";
    }

    @RequestMapping(value = "/user/followingPost")
    public String followingPost(Model model , HttpServletRequest request){
        model.addAttribute("followingPost" , "followingPost");
        String user_id = (String) request.getSession().getAttribute("user_id");
        String user_name = (String) request.getSession().getAttribute("user_name");
        Follow follow = new Follow();
        follow.setUser_id(user_id);
        follow.setTo_user_name(user_name);
        model.addAttribute("post" , service.followingPost(follow));
        model.addAttribute("user_name" , user_name);
        model.addAttribute("user_img_file" , (String) request.getSession().getAttribute("user_img_file"));
        return "post/postIndex";
    }

    @RequestMapping(value = "/user/post_Like_Comment_count")
    @ResponseBody
    public Post post_Like_Comment_count(int post_num){
        return service.post_Like_Comment_count(post_num);
    }

    @RequestMapping(value = "/user/selectPost")
    @ResponseBody
    public Post selectPost(int post_num){
        return service.selectPost(post_num);
    }

    @RequestMapping(value = "/user/insertPost")
    public String insertPost(Post post , HttpServletRequest request , @RequestParam("post_file") MultipartFile file){
        post.setUser_name((String) request.getSession().getAttribute("user_name"));
        post.setUser_id((String) request.getSession().getAttribute("user_id"));
        post.setUser_img_file((String) request.getSession().getAttribute("user_img_file"));
        post.setPost_img_file(file.getOriginalFilename());
        System.out.println(post.getUser_img_file());
        service.fileSave(file , (String) request.getSession().getAttribute("user_id"));
        service.insertPost(post);
        return "redirect:/user/newestPost";
    }

    @RequestMapping(value = "/user/updatePost")
    public String updatePost(Post post , HttpServletRequest request) throws UnsupportedEncodingException {
        post.setUser_name((String) request.getSession().getAttribute("user_name"));
        post.setUser_id((String) request.getSession().getAttribute("user_id"));
        service.updatePost(post);
        String encodedValue = URLEncoder.encode((String) request.getSession().getAttribute("user_name"), "UTF-8");
        return "redirect:/user/profilePost?pass=" + encodedValue;
    }

    @RequestMapping(value = "/user/deletePost")
    public String deletePost(@RequestParam(value = "pass") String post_num ,HttpServletRequest request) throws UnsupportedEncodingException{
        Post post = new Post();
        post.setPost_num(Integer.valueOf(post_num));
        post.setUser_id((String) request.getSession().getAttribute("user_id"));
        service.deletePost(post);
        String encodedValue = URLEncoder.encode((String) request.getSession().getAttribute("user_name"), "UTF-8");
        return "redirect:/user/profilePost?pass=" + encodedValue;
    }
}

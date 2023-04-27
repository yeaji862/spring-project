package spring.Pets.controller;

import spring.Pets.community.Comment;
import spring.Pets.community.CommunityService;
import spring.Pets.community.Follow;
import spring.Pets.community.Reply;
import spring.Pets.member.MemberService;
import spring.Pets.post.Post;
import spring.Pets.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    CommunityService service;

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/user/LikePost")
    public List<Integer> LikePost(HttpServletRequest request){
        return service.selectLike((String) request.getSession().getAttribute("user_name"));
    }
    @ResponseBody
    @RequestMapping(value = "/user/insertLike")
    public int insertLike(String post_num , HttpServletRequest request){
        Post post = new Post();
        post.setPost_num(Integer.valueOf(post_num));
        post.setUser_name((String) request.getSession().getAttribute("user_name"));
        System.out.println(post.toString());
        return service.insertLike(post);
    }
    @ResponseBody
    @RequestMapping(value = "/user/deleteLike")
    public int deleteLike(String post_num , HttpServletRequest request){
        Post post = new Post();
        post.setPost_num(Integer.valueOf(post_num));
        post.setUser_name((String) request.getSession().getAttribute("user_name"));
        return service.deleteLike(post);
    }

    @ResponseBody
    @RequestMapping(value = "/user/insertFollow")
    public int insertFollow(String from_user_name , HttpServletRequest request){
        Follow follow = Follow.followMapping((String) request.getSession().getAttribute("user_id") , (String) request.getSession().getAttribute("user_name")
                , from_user_name);
        memberService.userFollowUpdate(from_user_name);
        memberService.userFollowingUpdate((String) request.getSession().getAttribute("user_name"));
        return service.insertFollow(follow);
    }

    @ResponseBody
    @RequestMapping(value = "/user/deleteFollow")
    public int deleteFollow(String from_user_name , HttpServletRequest request){
        Follow follow = Follow.followMapping((String) request.getSession().getAttribute("user_id") , (String) request.getSession().getAttribute("user_name")
                , from_user_name);
        memberService.userFollowDelete(from_user_name);
        memberService.userFollowingDelete((String) request.getSession().getAttribute("user_name"));
        return service.deleteFollow(follow);
    }

    @ResponseBody
    @RequestMapping(value = "/user/followCount")
    public int followCount(String from_user_name){
        return service.followerList(from_user_name).size();
    }

    @ResponseBody
    @RequestMapping(value = "/user/selectFollow")
    public String selectFollow(String from_user_name , HttpServletRequest request){
        Follow follow = Follow.followMapping((String) request.getSession().getAttribute("user_id") , (String) request.getSession().getAttribute("user_name")
        , from_user_name);
        return service.selectFollow(follow);
    }

    @ResponseBody
    @RequestMapping(value = "/user/userComment")
    public List<Comment> userComment(String post_num){
        List<Comment> comments = service.selectComment(Integer.valueOf(post_num));
        List<Reply> replyList = service.selectReply(Integer.valueOf(post_num));
        for(int i=0; i<comments.size(); i++){
            List<Reply> replies = new ArrayList<>();
            for(int j=0; j<replyList.size(); j++){
                if(replyList.get(j).getPost_num() == comments.get(i).getPost_num() && replyList.get(j).getComment_num() == comments.get(i).getComment_num()){
                    replies.add(replyList.get(j));
                }
                comments.get(i).setReplyList(replies);
            }
        }
        return comments;
    }

    @ResponseBody
    @RequestMapping(value = "/user/userPostDetail")
    public Post userPost(String post_num){
        return postService.selectPost(Integer.valueOf(post_num));
    }

    @ResponseBody
    @RequestMapping(value = "/user/deleteComment")
    public int deleteComment(String post_num , String comment_num , HttpServletRequest request){
        System.out.println(post_num + comment_num);
        Comment comment = new Comment();
        comment.setUser_id((String) request.getSession().getAttribute("user_id"));
        comment.setComment_num(Integer.valueOf(comment_num));
        comment.setPost_num(Integer.valueOf(post_num));
        return service.deleteComment(comment);
    }

    @ResponseBody
    @RequestMapping(value = "/user/deleteReply")
    public int deleteReply(String post_num , String comment_num , String reply_num , HttpServletRequest request){
        Reply reply = new Reply();
        reply.setUser_id((String) request.getSession().getAttribute("user_id"));
        reply.setComment_num(Integer.valueOf(comment_num));
        reply.setPost_num(Integer.valueOf(post_num));
        reply.setReply_num(Integer.valueOf(reply_num));
        return service.deleteReply(reply);
    }

    @ResponseBody
    @RequestMapping(value = "/user/insertReply")
    public int insertReply(String comment_num , String post_num , String content , HttpServletRequest request){
        Reply reply = new Reply();
        reply.setComment_num(Integer.valueOf(comment_num));
        reply.setPost_num(Integer.valueOf(post_num));
        reply.setReply_content(content);
        reply.setUser_id((String) request.getSession().getAttribute("user_id"));
        reply.setUser_name((String) request.getSession().getAttribute("user_name"));
        reply.setUser_img_file((String) request.getSession().getAttribute("user_img_file"));
        return service.insertReply(reply);
    }

    @ResponseBody
    @RequestMapping(value = "/user/insertComment")
    public int insertComment(String post_num , String content , HttpServletRequest request){
        Comment comment = new Comment();
        comment.setUser_id((String) request.getSession().getAttribute("user_id"));
        comment.setComment_content(content);
        comment.setUser_name((String) request.getSession().getAttribute("user_name"));
        comment.setUser_img_file((String) request.getSession().getAttribute("user_img_file"));
        comment.setPost_num(Integer.valueOf(post_num));
        return service.insertComment(comment);
    }
}

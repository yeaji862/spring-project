package com.example.petsProject.communityImpl;

import com.example.petsProject.community.Comment;
import com.example.petsProject.community.CommunityService;
import com.example.petsProject.community.Follow;
import com.example.petsProject.community.Reply;
import com.example.petsProject.member.Member;
import com.example.petsProject.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CommunityService")
@Transactional
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    CommunityMapper mapper;
    @Override
    public int insertLike(Post post) {
        return mapper.insertLike(post);
    }

    @Override
    public int deleteLike(Post post) {
        return mapper.deleteLike(post);
    }

    @Override
    public List<Integer> selectLike(String user_name) {
        return mapper.selectLike(user_name);
    }

    @Override
    public List<Comment> selectComment(int post_num) {
        return mapper.selectComment(post_num);
    }

    @Override
    public List<Reply> selectReply(int post_num) {
        return mapper.selectReply(post_num);
    }

    @Override
    public int insertComment(Comment comment) {
        return mapper.insertComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        return mapper.updateComment(comment);
    }

    @Override
    public int deleteComment(Comment comment) {
        return mapper.deleteComment(comment);
    }

    @Override
    public int insertReply(Reply reply) {
        return mapper.insertReply(reply);
    }

    @Override
    public int updateReply(Reply reply) {
        return mapper.updateReply(reply);
    }

    @Override
    public int deleteReply(Reply reply) {
        return mapper.deleteReply(reply);
    }

    @Override
    public int insertFollow(Follow follow) {
        return mapper.insertFollow(follow);
    }

    @Override
    public int deleteFollow(Follow follow) {
        return mapper.deleteFollow(follow);
    }

    @Override
    public List<Member> followerList(String from_user_name) {
        return mapper.followerList(from_user_name);
    }

    @Override
    public List<Member> followingList(String to_user_name) {
        return mapper.followingList(to_user_name);
    }

    @Override
    public String selectFollow(Follow follow) {
        return mapper.selectFollow(follow);
    }
}

package com.example.petsProject.postImpl;

import com.example.petsProject.community.Follow;
import com.example.petsProject.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    List<Post> allPost(); // 게시물 전체 조회

    Post selectPost(int post_num); // 특정 게시물 검색

    List<Post> conSearchPost(String search); // 게시물 내용 기반 검색

    List<Post> profilePost(String user_id); // 게시물 전체 조회

    List<Post> newestPost(); // 최신순 조회

    List<Post> popularityPost(); // 인기순 조회

    List<Post> likePost(String user_id); // 좋아요 누른 게시물 조회

    List<Post> followingPost(Follow follow); // 팔로우 한 프로필 게시물 조회

    Post post_Like_Comment_count(int post_num); // 특정 게시물 좋아요 , 댓글 갯수

    int insertPost(Post post); // 게시물 등록

    int updatePost(Post post); // 게시물 수정

    int deletePost(Post post); // 게시물 삭제

}

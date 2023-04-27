package spring.Pets.communityImpl;

import spring.Pets.community.Comment;
import spring.Pets.community.Follow;
import spring.Pets.community.Reply;
import spring.Pets.member.Member;
import spring.Pets.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommunityMapper {

    int insertLike(Post post); // 좋아요 등록

    int deleteLike(Post post); // 좋아요 해지

    List<Integer> selectLike(String user_name); // 좋아요 누른 게시물 번호

    List<Comment> selectComment(int post_num); // 댓글 검색

    List<Reply> selectReply(int post_num); // 대댓글 검색

    int insertComment(Comment comment); // 댓글 등록

    int updateComment(Comment comment); // 댓글 수정

    int deleteComment(Comment comment); // 댓글 삭제

    int insertReply(Reply reply); // 대댓글 등록

    int updateReply(Reply reply); // 대댓글 수정

    int deleteReply(Reply reply); // 대댓글 삭제

    int insertFollow(Follow follow); // 팔로우 하기

    int deleteFollow(Follow follow); // 팔로우 해지

    List<Member> followerList(String from_user_name); // 팔로워 리스트

    List<Member> followingList(String to_user_name); // 팔로잉 리스트

    String selectFollow(Follow follow); // 팔로우 검색

}

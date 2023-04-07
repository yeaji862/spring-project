package com.example.petsProject.postImpl;

import com.example.petsProject.community.Follow;
import com.example.petsProject.post.Post;
import com.example.petsProject.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    PostMapper mapper;
    @Override
    public List<Post> allPost() {
        return mapper.allPost();
    }

    @Override
    public Post selectPost(int post_num) {
        return mapper.selectPost(post_num);
    }

    @Override
    public List<Post> conSearchPost(String search) {
        return mapper.conSearchPost(search);
    }

    @Override
    public List<Post> profilePost(String user_id) {
        return mapper.profilePost(user_id);
    }

    @Override
    public List<Post> newestPost() {
        return mapper.newestPost();
    }

    @Override
    public List<Post> popularityPost() {
        return mapper.popularityPost();
    }

    @Override
    public List<Post> likePost(String user_id) {
        return mapper.likePost(user_id);
    }

    @Override
    public List<Post> followingPost(Follow follow) {
        return mapper.followingPost(follow);
    }

    @Override
    public Post post_Like_Comment_count(int post_num) {
        return mapper.post_Like_Comment_count(post_num);
    }

    @Override
    public int insertPost(Post post) {
        return mapper.insertPost(post);
    }

    @Override
    public int updatePost(Post post) {
        return mapper.updatePost(post);
    }

    @Override
    public int deletePost(Post post) {
        return mapper.deletePost(post);
    }

    @Override
    public void fileSave(MultipartFile file, String user_id) {
        try{
            if(!file.isEmpty()){
                String fullPath = uploadPath + "/post/" + file.getOriginalFilename();
                file.transferTo(new File(fullPath));
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}

package com.geekster.instagram.service;

import com.geekster.instagram.dao.PostRepository;
import com.geekster.instagram.dao.UserRepository;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    public  int savePost(Post post) {
        Post post1= postRepository.save(post);
        return  post1.getPostId();
    }

    public JSONArray getPost(String userId, String postId) {

        JSONArray postArr=new JSONArray();

        if(postId !=null && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post post =postRepository.findById(Integer.valueOf(postId)).get();
            JSONObject jsonObject= setPost(post);
            postArr.put(jsonObject);
        } else {
            List<Post>postList = postRepository.findAll();

            for(Post post:postList){
                if(post.getUser().getUserId()==Integer.valueOf(userId)){
                    postArr.put(post);
                }
            }
        }

        return postArr;

    }

    private JSONObject setPost(Post post) {
        JSONObject postJson =new JSONObject();
        postJson.put("postId",post.getPostId());
        postJson.put("postData",post.getPostData());

        User user = post.getUser();

        JSONObject userJson =new JSONObject();
        userJson.put("userId",user.getUserId());
        userJson.put("firstName",user.getFirstName());
        userJson.put("lastName",user.getLastName());
        userJson.put("age",user.getAge());

        postJson.put("user",userJson);

        return postJson;
    }
}

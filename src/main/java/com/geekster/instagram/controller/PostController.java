package com.geekster.instagram.controller;



import com.geekster.instagram.dao.UserRepository;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.User;
import com.geekster.instagram.service.PostService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity  savePost(@RequestBody String postData){
        Post post = setPost(postData);

        int postId= postService.savePost(post);

        return new ResponseEntity(postId, HttpStatus.CREATED);
    }

    @GetMapping("/post")

    public ResponseEntity getPost(@RequestParam String userId, @Nullable @RequestParam String postId){
        JSONArray postArr=postService.getPost(userId,postId);

        return new ResponseEntity<>(postArr.toString(),HttpStatus.OK);

    }



    private Post setPost(String postData) {

        JSONObject jsonObject =new JSONObject(postData);
        int userId=jsonObject.getInt("userId");
        User user =null;

        if(userRepository.findById(userId).isPresent()){
            user =  userRepository.findById(userId).get();

        }else{
            return  null;
        }

        Post post =new Post();

        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));
        Timestamp createdTime =new Timestamp(System.currentTimeMillis());
        post.setCreatedDate(createdTime);

        return post;


    }

}

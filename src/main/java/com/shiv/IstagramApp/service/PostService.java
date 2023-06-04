package com.shiv.IstagramApp.service;


import com.shiv.IstagramApp.model.Post;
import com.shiv.IstagramApp.model.User;
import com.shiv.IstagramApp.repo.IPostRepo;
import com.shiv.IstagramApp.repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    @Autowired
    ITokenRepo tokenRepo;
    public void addPost(Post post) {
        postRepo.save(post);
    }

    public List<Post> getAllPosts(String token) {
        User user = tokenRepo.findFirstByToken(token).getUser();


        List<Post> postList = postRepo.findByUser(user);

        return postList;


    }
}

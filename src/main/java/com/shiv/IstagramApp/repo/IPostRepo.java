package com.shiv.IstagramApp.repo;

import com.shiv.IstagramApp.model.Post;
import com.shiv.IstagramApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
}

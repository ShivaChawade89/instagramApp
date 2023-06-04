package com.shiv.IstagramApp.repo;


import com.shiv.IstagramApp.model.InstagramComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<InstagramComment, Long> {
}

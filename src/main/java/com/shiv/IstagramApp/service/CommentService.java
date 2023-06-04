package com.shiv.IstagramApp.service;


import com.shiv.IstagramApp.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;
}

package com.shiv.IstagramApp.service;


import com.shiv.IstagramApp.repo.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


    @Autowired
    private IAdminRepo adminRepo;
}

package com.shiv.IstagramApp.repo;


import com.shiv.IstagramApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Long> {
}

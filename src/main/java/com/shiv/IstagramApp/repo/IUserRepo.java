package com.shiv.IstagramApp.repo;


import com.shiv.IstagramApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);
}

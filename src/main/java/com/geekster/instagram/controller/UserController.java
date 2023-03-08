package com.geekster.instagram.controller;

import com.geekster.instagram.model.User;
import com.geekster.instagram.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/user")
    public  ResponseEntity saveUser (@RequestBody String userData){
        User user= setUser(userData);
        int userId=userService.saveUser(user);
        return new ResponseEntity<>("save user"+ userId , HttpStatus.CREATED );
    }

    @GetMapping("/user")

    public ResponseEntity getUser(@Nullable @RequestParam String userId){

        JSONArray jsonArray=userService.getUser(userId);

        return new ResponseEntity(jsonArray.toString(),HttpStatus.OK);

    }

    @PutMapping("/user/userId")
    public ResponseEntity updateUserDetails(@PathVariable String userId,@RequestBody String userDetails){
        User user =setUser(userDetails);
        userService.updateUserDetails(user ,userId);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }

    private User setUser(String userData) {
        JSONObject jsonObject=new JSONObject(userData);
        User user =new User();
        user.setAge(jsonObject.getInt("age"));
        user.setEmail(jsonObject.getString("email"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setPhoneNumber(jsonObject.getString("number"));

        return user;

    }
}

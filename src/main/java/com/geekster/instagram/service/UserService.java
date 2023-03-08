package com.geekster.instagram.service;

import com.geekster.instagram.dao.UserRepository;
import com.geekster.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public int saveUser(User user) {
       User user1= userRepository.save(user);
       return user1.getUserId();
    }


    public JSONArray getUser(String userId) {

        JSONArray jsonArray =new JSONArray();
        if(userId != null&&userRepository.findById(Integer.valueOf(userId)).isPresent()){
            User user =userRepository.findById(Integer.valueOf(userId)).get();
                JSONObject jsonObject = setUser(user);
                jsonArray.put(jsonObject);
            }else{
                List<User> usersList =userRepository.findAll();
                for(User usr: usersList){
                    JSONObject jsonObject=setUser(usr);
                    jsonArray.put(jsonObject);



                }
            }
        return jsonArray;
        }



    private JSONObject setUser(User user) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("firstName",user.getFirstName());
        jsonObject.put("lastName",user.getLastName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());

        return  jsonObject;

    }


    public void updateUserDetails(User newUser, String userId) {
        if(userRepository.findById(Integer.valueOf(userId)).isPresent()){
            User user=userRepository.findById(Integer.valueOf(userId)).get();

            newUser.setUserId(user.getUserId());
            userRepository.save(newUser);
        }
    }
}

package com.shiv.IstagramApp.controller;

import com.shiv.IstagramApp.dto.SignInInput;
import com.shiv.IstagramApp.dto.SignInOutput;
import com.shiv.IstagramApp.dto.SignUpOutput;
import com.shiv.IstagramApp.model.User;
import com.shiv.IstagramApp.service.TokenService;
import com.shiv.IstagramApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService authService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody User signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            authService.deleteToken(token);
            msg = "Signout Successful";
            status = HttpStatus.OK;

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestParam String email , @RequestParam String token , @RequestBody User user){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            try{
                userService.updateUser(user , token);
                status = HttpStatus.OK;
                msg = "User updated sucessfully";
            }catch (Exception e){
                msg = "Enter valid information";
                status = HttpStatus.BAD_REQUEST;
            }

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }


}

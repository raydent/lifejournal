package com.example.lifejournal.controller;

import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.model.UsernameAndPassword;
import com.example.lifejournal.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    @ResponseBody
    public String signUpUser(@RequestBody UsernameAndPassword usernameAndPassword) {
        try {
            return authService.createUser(usernameAndPassword).toString();
        } catch (CustomException e) {
            return e.getErrorDescription();
        }
    }

    @PostMapping("/signin")
    @ResponseBody
    public String signInUser(@RequestBody UsernameAndPassword usernameAndPassword) {
        User user;
        try {
            user = authService.authUser(usernameAndPassword);
        }
        catch (CustomException e){
            return e.getErrorDescription();
        }
        return user.toString();
    }

    /*@PostMapping("/post")
    @ResponseBody
    public String createPost(@RequestBody PostData postData) {
        Post post = new Post();
        try{
            post = authService.createPost(postData);
        }
        catch (CustomException e){
            return e.getErrorDescription();
        }
        return post.toString();
    }*/


}

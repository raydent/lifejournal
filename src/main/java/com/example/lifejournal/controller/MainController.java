package com.example.lifejournal.controller;

import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.service.IPostService;
import com.example.lifejournal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping("/get/userNumber")
    @ResponseBody
    public int getUserNumber(){
        return userService.getAllUsers().size();
    }

    @GetMapping("/get/postNumber")
    @ResponseBody
    public int getPostNumber(){
        return postService.getAllPosts().size();
    }

    @GetMapping("/get/posts")
    @ResponseBody
    public String getPosts(){
        return postService.getAllPosts().toString();
    }

    @GetMapping("/get/users")
    @ResponseBody
    public String getUsers(){
        return userService.getAllUsers().toString();
    }

    @PostMapping("/create/user")
    @ResponseBody
    public String createUser(@RequestBody User user){
        return Optional.ofNullable(userService.save(user)).
                map(User::toString).
                orElse("NULL");
    }

    @PostMapping("/create/post")
    @ResponseBody
    public String createPost(@RequestBody PostData postData) {
        if (!userService.checkPassword(postData.getCreatorId(), postData.getPassword())){
            return "Incorrect login data";
        }
        Post post = new Post();
        post.setText(postData.getText());
        post.setId(postData.getId());
        post.setCreatorId(postData.getCreatorId());
        post.setPostName(postData.getPostName());
        return  Optional.ofNullable(postService.save(post)).
                map(Post::toString).
                orElse("Null");
    }

}

package com.example.lifejournal.controller;

import com.example.lifejournal.model.UserPost;
import com.example.lifejournal.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {
    private int maxUserId = 0;
    private int maxPostId = 0;
    private List<UserPost> posts = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @GetMapping("/get/userNumber")
    @ResponseBody
    public int getUserNumber(){
        return maxUserId;
    }

    @GetMapping("/get/postNumber")
    @ResponseBody
    public int getPostNumber(){
        return maxPostId;
    }

    @PostMapping(value = "/create/user")
    @ResponseBody
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("password") String password){
        User user = new User(maxUserId, name, password);
        users.add(user);
        maxUserId++;
        return user.toString();
    }

    @PostMapping("/create/post")
    @ResponseBody
    public String createPost(@RequestParam("userId") int userId,
                             @RequestParam("text") String text,
                             @RequestParam("password") String password){
        if (maxUserId <= userId){
            return "Invalid user id";
        }
        if (!users.get(userId).checkPassword(password)){
            return "Invalid password";
        }
        UserPost userPost = new UserPost(maxPostId, userId, users.get(userId).getName(), text);
        posts.add(userPost);
        maxPostId++;
        return userPost.toString();
    }

    @PostMapping("/vote")
    @ResponseBody
    public String vote(@RequestParam("userId") int userId,
                       @RequestParam("postId") int postId,
                       @RequestParam("value") int value,
                       @RequestParam("password") String password){
        if (maxUserId <= userId){
            return "Invalid userId";
        }
        if (maxPostId <= postId){
            return "Invalid postId";
        }
        if (value == 0){
            return "Invalid vote value";
        }
        int val = users.get(userId).vote(value / Math.abs(value), postId, password);
        posts.get(postId).changeRating(val);
        return posts.get(postId).toString();
    }


    @GetMapping("/get/posts")
    @ResponseBody
    public String getPosts(){
        return posts.toString();
    }
    @GetMapping("/get/users")
    @ResponseBody
    public String getUsers(){
        return users.toString();
    }
}

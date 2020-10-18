package com.example.lifejournal.controller;

import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Post> posts = new HashMap<>();
    private Map<String, HashMap<String, Integer>> votes = new HashMap<>(); //String = post name, K = username, v = vote value (1 || -1)

    boolean checkPasswordAndUserName(String userName, String password) {
        if (!users.containsKey(userName)) {
            return false;
        }
        if (!users.get(userName).checkPassword(password)) {
            return false;
        }
        return true;
    }

    @GetMapping("/get/userNumber")
    @ResponseBody
    public int getUserNumber(){
        return users.size();
    }

    @GetMapping("/get/postNumber")
    @ResponseBody
    public int getPostNumber(){
        return posts.size();
    }

    @GetMapping("/get/posts")
    @ResponseBody
    public String getPosts(){
        return posts.values().toString();
    }

    @GetMapping("/get/users")
    @ResponseBody
    public String getUsers(){
        return users.values().toString();
    }

    @PostMapping("/create/user")
    @ResponseBody
    public String createUser(@RequestBody User user){
        if (users.containsKey(user.getName())){
            return "Name already taken";
        }
        users.put(user.getName() , user);
        return user.toString();
    }

    @PostMapping("/create/post/{password}")
    @ResponseBody
    public String createPost(@PathVariable("password") String password , @RequestBody Post post){
        if (!checkPasswordAndUserName(post.getCreatorName(), password)){
            return "Wrong username or password";
        }
        if (posts.containsKey(post.getPostName())){
            return "Post name already taken";
        }
        posts.put(post.getPostName(), post);
        return post.toString();
    }

    @PostMapping("/likeOrDislike")
    @ResponseBody
    public String likeOrDislike(@RequestParam("userName") String userName,
                       @RequestParam("postName") String postName,
                       @RequestParam("value") int value,
                       @RequestParam("password") String password){
        if (!checkPasswordAndUserName(userName, password)){
            return "Wrong username or password";
        }
        if (!posts.containsKey(postName)){
            return "Invalid post name";
        }
        if (value == 0){
            return "Invalid vote value";
        }
        int val = 0;
        if (votes.get(postName) != null){
            if (votes.get(postName).get(userName) != null){
                val = votes.get(postName).get(userName);
            }
        }
        value /= Math.abs(value);
        value -= val;
        if (votes.get(postName) != null){
            votes.get(postName).put(userName, value);
        }
        else {
            HashMap<String, Integer> submap = new HashMap<>();
            submap.put(userName, value);
            votes.put(postName, submap);
        }
        posts.get(postName).changeRating(value);
        return posts.get(postName).toString();
    }

}

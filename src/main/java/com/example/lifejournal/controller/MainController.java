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
    //private List<UserPost> posts = new ArrayList<>();
    private Map<Integer, UserPost> idToPost = new TreeMap<>();
    //private Set<Integer> userIdSet = new HashSet<>();
    private List<User> users = new ArrayList<>();

    @PostMapping(value = "/create/user")
    @ResponseBody
    public String createUser(@RequestParam("name") String name){
        User user = new User(maxUserId, name);
        users.add(user);
       // userIdSet.add(maxUserId);
        maxUserId++;
        return user.toString();
    }

    @PostMapping("/create/post")
    @ResponseBody
    public String createPost(@RequestParam("userId") int userId, @RequestParam("text") String text){
        if (maxUserId <= userId){
            return "Invalid user id";
        }
        UserPost post = new UserPost(maxPostId, userId, users.get(userId).getName(), text);
        idToPost.put(maxPostId, post);
        maxPostId++;
        return post.toString();
    }

    @PostMapping("/vote")
    @ResponseBody
    public String vote(@RequestParam("userId") int userId, @RequestParam("postId") int postId, @RequestParam("value") int value){
        if (maxUserId <= userId){
            return "Invalid userId";
        }
        if (!idToPost.containsKey(postId)){
            return "Invalid postId";
        }
        if (value == 0){
            return "Invalid vote value";
        }
        int val = users.get(userId).vote(value / Math.abs(value), postId);
        idToPost.get(postId).changeRating(val);
        return idToPost.get(postId).toString();
    }


    @GetMapping("/get/posts")
    @ResponseBody
    public String getPosts(){
        return idToPost.values().toString();
    }
    @GetMapping("/get/users")
    @ResponseBody
    public String getUsers(){
        return users.toString();
    }
}

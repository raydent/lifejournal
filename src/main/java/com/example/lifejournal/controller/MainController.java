package com.example.lifejournal.controller;

import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.security.SpringSecurityConfig;
import com.example.lifejournal.service.IPostService;
import com.example.lifejournal.service.IUserService;
import com.example.lifejournal.security.SpringSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;
    @Autowired
    AuthenticationManager authenticationManager;


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

    /*@GetMapping("/get/users")
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
    }*/

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
    @GetMapping("/about")
    public String about(){
        return "/about";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("return1");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            System.out.println("return2");
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @AuthenticationPrincipal User authenticatedUser) {
        System.out.println(model.toString());
        if (Objects.nonNull(authenticatedUser)) {
            return "redirect:/user";
        }
        model.addAttribute("userForm", new User());
        return "login";
    }

    @PostMapping("/login")
    public String setLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userForm") User user,
                           BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), user.getAuthorities());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return "redirect:/user";
    }

    @GetMapping("/post")
    public String getPost(Model model) {
        model.addAttribute("postForm", new Post());
        return "post";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute("postForm") Post postForm, Model model) {
        System.out.println("Started");
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        postForm.setId(userService.getUserByName(currentPrincipalName).getId());*/
        int creatorId  = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        postForm.setCreatorId(creatorId);
        //System.out.println(postForm.toString());
        postService.save(postForm);
        return "redirect:/get/posts";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "/admin";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error";
    }

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }
}

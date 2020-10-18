package com.example.lifejournal.service;

import com.example.lifejournal.CustomErrors;
import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.model.UsernameAndPassword;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBExample implements IDBExample {

    private List<User> createdUsers = new ArrayList<>();

    private List<Post> createdPosts = new ArrayList<>();

    @Override
    public List<User> getCreatedUsers() {
        return createdUsers;
    }

    @Override
    public void setCreatedUsers(List<User> createdUsers) {
        this.createdUsers = createdUsers;
    }

    @Override
    public User createUser(User user) throws CustomException {
        if (this.createdUsers.stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst()
                .orElse(null) == null) {
            user.setId(getId());
            createdUsers.add(user);
            return user;
        }
        else {
            throw CustomErrors.USER_WITH_SUCH_USERNAME_FOUND.getException();
        }
    }

    @Override
    public List<Post> getCreatedPosts() {
        return createdPosts;
    }

    @Override
    public void setCreatedPosts(List<Post> createdPosts) {
        this.createdPosts = createdPosts;
    }

    @Override
    public Post createPost(Post post) throws CustomException {
        post.setId(this.getPostId());
        if (this.createdPosts.stream()
                .filter(u -> u.getPostName().equals(post.getPostName()))
                .findFirst()
                .orElse(null) == null){
            createdPosts.add(post);
            return post;
        }
        else {
            throw CustomErrors.POST_WITH_SUCH_POSTNAME_FOUND.getException();
        }
    }

    private Integer getId() {
        return this.createdUsers.size() + 1;
    }

    private Integer getPostId(){
        return this.createdPosts.size() + 1;
    }
}

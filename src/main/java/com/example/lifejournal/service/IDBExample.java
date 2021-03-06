package com.example.lifejournal.service;


import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.model.UsernameAndPassword;

import java.util.ArrayList;
import java.util.List;

public interface IDBExample {

    public List<User> getCreatedUsers();

    public void setCreatedUsers(List<User> createdUsers);

    public User createUser(User user) throws CustomException;

    public List<Post> getCreatedPosts();

    public void setCreatedPosts(List<Post> createdPosts);

    public Post createPost(Post post) throws CustomException;

}

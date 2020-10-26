package com.example.lifejournal.service;


import com.example.lifejournal.model.Post;

import java.util.List;

public interface IPostService {
    Post getPostById(Integer id);
    Post save(Post post);
    void deleteById(Integer id);

    List<Post> getAllPosts();
}
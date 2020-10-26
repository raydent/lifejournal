package com.example.lifejournal.service;

import com.example.lifejournal.model.Post;
import com.example.lifejournal.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService  implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}

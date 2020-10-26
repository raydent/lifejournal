package com.example.lifejournal.service;

import com.example.lifejournal.CustomErrors;
import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.model.UsernameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IDBExample dbExample;

    @Override
    public User authUser(UsernameAndPassword usernameAndPassword) throws CustomException {
        List<User> users = this.dbExample.getCreatedUsers();
        for (User user: users) {
            if (user.getName().equals(usernameAndPassword.getUsername())){
                if (user.checkPassword(usernameAndPassword.getPassword())) {
                    return user;
                }
                throw CustomErrors.INVALID_PASSWORD.getException();
            }
        }
        throw CustomErrors.NO_USER_FOUND.getException();
    }

    @Override
    public User createUser(UsernameAndPassword usernameAndPassword) throws CustomException {
        if (usernameAndPassword.getPassword().length() < 7) {
            throw CustomErrors.PASSWORD_HAS_INVALID_LENGTH.getException();
        }
        User user = new User();
        user.setName(usernameAndPassword.getUsername());
        user.setPassword(usernameAndPassword.getPassword());
        return dbExample.createUser(user);
    }

    @Override
    public Post createPost(PostData postData) throws CustomException {
        UsernameAndPassword usernameAndPassword = new UsernameAndPassword();
        usernameAndPassword.setPassword(postData.getPassword());
        usernameAndPassword.setUsername(postData.getCreatorName());
        this.authUser(usernameAndPassword);
        Post post = new Post();
        post.setText(postData.getText());
        post.setCreatorId(postData.getCreatorId());
        post.setPostName(postData.getPostName());
        return dbExample.createPost(post);
    }
}

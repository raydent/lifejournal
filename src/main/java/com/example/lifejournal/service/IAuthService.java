package com.example.lifejournal.service;

import com.example.lifejournal.CustomErrors;
import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.Post;
import com.example.lifejournal.model.PostData;
import com.example.lifejournal.model.User;
import com.example.lifejournal.model.UsernameAndPassword;

public interface IAuthService {
    User authUser(UsernameAndPassword usernameAndPassword) throws CustomException;
    User createUser(UsernameAndPassword usernameAndPassword) throws CustomException;
    Post createPost(PostData postData) throws CustomException;
}

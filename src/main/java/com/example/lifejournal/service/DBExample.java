package com.example.lifejournal.service;

import com.example.lifejournal.CustomErrors;
import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBExample implements IDBExample {

    private List<User> createdUsers = new ArrayList<>();

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

    private Integer getId() {
        return this.createdUsers.size() + 1;
    }
}

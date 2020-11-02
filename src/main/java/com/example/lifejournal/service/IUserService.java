package com.example.lifejournal.service;

import com.example.lifejournal.model.User;

import java.util.List;

public interface IUserService {
    User getUserById(Integer id);
    User getUserByName(String name);
    User save(User user);
    void deleteById(Integer id);
    public boolean saveUser(User user);
    List<User> getAllUsers();
    User createUser(Integer id, String name);
    boolean checkPassword(Integer id, String password);
}

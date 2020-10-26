package com.example.lifejournal.service;

import com.example.lifejournal.model.User;
import com.example.lifejournal.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return userRepository.save(user);
    }

    @Override
    public boolean checkPassword(Integer id, String password) {
        return userRepository.findUserById(id).checkPassword(password);
    }
}

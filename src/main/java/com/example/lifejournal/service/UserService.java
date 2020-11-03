package com.example.lifejournal.service;

import com.example.lifejournal.model.Role;
import com.example.lifejournal.model.User;
import com.example.lifejournal.repo.RoleRepository;
import com.example.lifejournal.repo.UserRepository;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User save(User user) {
        User userFromDB = userRepository.findUserByName(user.getName());
        if (userFromDB == null) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findUserByName(user.getName());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(name);
        System.out.println("name = '" + name + "'");
        System.out.println("load = " + ((user == null) ? "null" : user.toString()));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}

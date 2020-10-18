package com.example.lifejournal.service;


import com.example.lifejournal.CustomException;
import com.example.lifejournal.model.User;

import java.util.ArrayList;
import java.util.List;

public interface IDBExample {

    public List<User> getCreatedUsers();

    public void setCreatedUsers(List<User> createdUsers);

    public User createUser(User user) throws CustomException;

}

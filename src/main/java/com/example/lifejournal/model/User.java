package com.example.lifejournal.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {
    //private static int maxUserId = 0;
    private int id = 0;
    private String name = null;
    private Map<Integer, Integer> votedPosts = new HashMap<>();
    private String password = null;

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public int vote(int voteValue, int postId, String password){
        int previousVoteValue = 0;
        if (checkPassword(password)) {
            if (votedPosts.containsKey(postId)) {
                previousVoteValue = votedPosts.get(postId);
            }
            votedPosts.put(postId, voteValue);
            return voteValue - previousVoteValue;
        }
        else{
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\''+
                ", votedPosts=" + votedPosts +
                '}';
    }
}

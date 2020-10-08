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

    public int vote(int voteValue, int postId){
        int previousVoteValue = 0;
        if (votedPosts.containsKey(postId)){
            previousVoteValue = votedPosts.get(postId);
        }
        votedPosts.put(postId, voteValue);
        return voteValue - previousVoteValue;
    }

    public String getName() {
        return name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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

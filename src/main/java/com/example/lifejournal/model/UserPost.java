package com.example.lifejournal.model;

public class UserPost {
    private String creatorName = null;
    private String text = null;
    private int rating = 0;
    private int creatorId = 0;
    private int postId = 0;

    public String getCreatorName() {
        return creatorName;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "creatorName='" + creatorName + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", creatorId=" + creatorId +
                ", postId=" + postId +
                '}';
    }

    public UserPost(int postId, int creatorId, String creatorName, String text) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.text = text;
    }
    public void changeRating(int value){
        rating += value;
    }
}

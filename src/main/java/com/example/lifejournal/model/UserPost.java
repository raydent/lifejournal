package com.example.lifejournal.model;

public class UserPost {
    private String creatorName = null;
    private String text = null;
    private String postName = null;
    private int rating = 0;
    public String getCreatorName() {
        return creatorName;
    }

    public String getText() {
        return text;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getRating() {
        return rating;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(int rating) {
        this.rating = 0;
    }


    @Override
    public String toString() {
        return "UserPost{" +
                "creatorName='" + creatorName + '\'' +
                ", text='" + text + '\'' +
                ", postName='" + postName + '\'' +
                ", rating=" + rating +
                '}';
    }

    public void changeRating(int value){
        rating += value;
    }
}

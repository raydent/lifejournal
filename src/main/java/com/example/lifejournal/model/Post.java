package com.example.lifejournal.model;

import java.util.Objects;

public class Post {
    private String creatorName = null;
    private String text = null;
    private String postName = null;
    private int rating = 0;
    private int id;



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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Post{" +
                "creatorName='" + creatorName + '\'' +
                ", text='" + text + '\'' +
                ", postName='" + postName + '\'' +
                ", rating=" + rating +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return rating == post.rating &&
                id == post.id &&
                creatorName.equals(post.creatorName) &&
                text.equals(post.text) &&
                postName.equals(post.postName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void changeRating(int value){
        rating += value;
    }
}

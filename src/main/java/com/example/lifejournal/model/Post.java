package com.example.lifejournal.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    private Integer creatorId = null;
    private String text = null;
    private String postName = null;
    private int rating = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getCreatorId() {
        return creatorId;
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

    public void setCreatorName(Integer creatorId) {
        this.creatorId = creatorId;
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
                "creatoId='" + creatorId + '\'' +
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
                creatorId.equals(post.creatorId) &&
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

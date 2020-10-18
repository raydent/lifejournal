package com.example.lifejournal.model;

public class PostData {
    private String creatorName;
    private String password;
    private String text;
    private String postName;

    public String getCreatorName() {
        return creatorName;
    }

    public void setUserName(String userName) {
        this.creatorName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}

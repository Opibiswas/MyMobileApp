package com.example.mymobileapp;

public class BlogPost {
    private String caption;
    private String photoUrl;
    private int likes;
    private int dislikes;

    public BlogPost() {
        // Default constructor required for Firebase
    }

    public BlogPost(String caption, String photoUrl, int likes, int dislikes) {
        this.caption = caption;
        this.photoUrl = photoUrl;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}

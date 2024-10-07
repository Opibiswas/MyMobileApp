package com.example.mymobileapp;
public class PhotoDetails {
    private String photoUrl;
    private String caption;
    private int likes;
    private int dislikes;

    public PhotoDetails(String photoUrl, String caption, int likes, int dislikes) {
        this.photoUrl = photoUrl;
        this.caption = caption;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    // Constructors, getters, and setters
}

package com.tunanh.ex10;

public class Album {
    private int id;
    private String avt_url,title;

    public Album(int id, String avt_url, String title) {
        this.id = id;
        this.avt_url = avt_url;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvt_url() {
        return avt_url;
    }

    public void setAvt_url(String avt_url) {
        this.avt_url = avt_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

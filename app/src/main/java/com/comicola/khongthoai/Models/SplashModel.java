package com.comicola.khongthoai.Models;

public class SplashModel {
    int img;
    String title;
    String detail;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public SplashModel() {
    }

    public SplashModel(int img, String title, String detail) {
        this.img = img;
        this.title = title;
        this.detail = detail;
    }
}

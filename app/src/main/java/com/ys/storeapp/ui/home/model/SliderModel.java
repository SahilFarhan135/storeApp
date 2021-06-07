package com.ys.storeapp.ui.home.model;

public class SliderModel {

    private int img;
    private int backgroundColor;

    public SliderModel(int img, int backgroundColor) {
        this.img = img;
        this.backgroundColor = backgroundColor;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}

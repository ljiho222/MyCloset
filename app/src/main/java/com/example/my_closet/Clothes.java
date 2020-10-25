package com.example.my_closet;

import android.graphics.drawable.Drawable;

public class Clothes {
    private String color;
    private String type1;
    private String type2;
    private Drawable url;

    public Clothes(){}

    public Clothes(String color, String type1, String type2, Drawable url){
        this.color = color;
        this.type1 = type1;
        this.type2 = type2;
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Drawable getUrl() {
        return url;
    }

    public void setUrl(Drawable url) {
        this.url = url;
    }
}

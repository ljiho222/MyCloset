package com.example.my_closet;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Clothes {
    private String color;
    private String type1;
    private String type2;
    private String style;
    private String url;
    private String key;

    public Clothes(){}

    public Clothes(String color, String type1, String type2, String style, String url, String key){
        this.color = color;
        this.type1 = type1;
        this.type2 = type2;
        this.style = style;
        this.url = url;
        this.key = key;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}

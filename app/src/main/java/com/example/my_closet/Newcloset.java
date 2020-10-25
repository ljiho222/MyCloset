package com.example.my_closet;

public class Newcloset {
    private String name;
    private int style;


    public Newcloset(){
        name = "";
    }

    public Newcloset(String name, int style)
    {
        this.name = name;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}

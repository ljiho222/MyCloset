package com.example.my_closet;

public class SearchedData {
    String url, closet, style;

    public SearchedData(String closet, String style, String url) {
        this.url = url;
        this.closet = closet;
        this.style = style;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCloset() {
        return closet;
    }

    public void setCloset(String closet) {
        this.closet = closet;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}

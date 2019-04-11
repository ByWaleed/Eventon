package com.bywaleed.eventon;

public class event {

    int background;
    int logo;
    String title;
    String date;

    public event() {
    }

    public event(int background, int logo, String title, String date) {
        this.background = background;
        this.logo = logo;
        this.title = title;
        this.date = date;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

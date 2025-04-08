package com.example.aba_bank.ui.model;

public class Announcements {
    private String dmy;
    private int images;
    private String title;
    private String txtDescription;

    public Announcements(String dmy, int images, String title, String txtDescription) {
        this.dmy = dmy;
        this.images = images;
        this.title = title;
        this.txtDescription = txtDescription;
    }

    public String getDmy() {
        return dmy;
    }

    public void setDmy(String dmy) {
        this.dmy = dmy;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }
}

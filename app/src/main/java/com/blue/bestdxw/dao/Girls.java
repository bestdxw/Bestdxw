package com.blue.bestdxw.dao;

import org.litepal.crud.LitePalSupport;

/**
 * LitePalSupport
 * @author Blue
 * @date 2018/6/14 0014 22:30
 */
public class Girls extends LitePalSupport {
    private int id;
    private String titie;
    private String imageUrl;
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitie() {
        return titie;
    }

    public void setTitie(String titie) {
        this.titie = titie;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

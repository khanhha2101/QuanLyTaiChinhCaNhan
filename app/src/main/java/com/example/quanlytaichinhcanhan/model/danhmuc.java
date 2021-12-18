package com.example.quanlytaichinhcanhan.model;

import java.io.Serializable;

public class danhmuc implements Serializable {
    private int iddm;
    private String tendm, image;
    private int phanloai;

    public danhmuc(int iddm, String tendm, String image, int phanloai) {
        this.iddm = iddm;
        this.tendm = tendm;
        this.image = image;
        this.phanloai = phanloai;
    }

    public int getIddm() {
        return iddm;
    }

    public void setIddm(int iddm) {
        this.iddm = iddm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(int phanloai) {
        this.phanloai = phanloai;
    }
}

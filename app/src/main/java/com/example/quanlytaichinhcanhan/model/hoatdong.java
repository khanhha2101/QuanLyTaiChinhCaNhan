package com.example.quanlytaichinhcanhan.model;

public class hoatdong {
    private int idhd;
    private int idnd;
    private int iddm;
    private String tendm;
    private String image;
    private int phanloai;
    private float sotien;
    private String ghichu;
    private int ngay;
    private int thang;
    private int nam;


    public hoatdong(int idhd, int idnd, int iddm, String tendm, String image, int phanloai, float sotien, String ghichu, int ngay, int thang, int nam) {
        this.idhd = idhd;
        this.idnd = idnd;
        this.iddm = iddm;
        this.tendm = tendm;
        this.image = image;
        this.phanloai = phanloai;
        this.sotien = sotien;
        this.ghichu = ghichu;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public int getIdnd() {
        return idnd;
    }

    public void setIdnd(int idnd) {
        this.idnd = idnd;
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

    public float getSotien() {
        return sotien;
    }

    public void setSotien(float sotien) {
        this.sotien = sotien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
}

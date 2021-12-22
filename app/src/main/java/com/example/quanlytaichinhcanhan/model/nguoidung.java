package com.example.quanlytaichinhcanhan.model;

import java.io.Serializable;

public class nguoidung implements Serializable {
    private int idnd;
    private String taikhoan, matkhau, hoten;

    public nguoidung(int idnd, String taikhoan, String matkhau, String hoten) {
        this.idnd = idnd;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
    }

    public int getIdnd() {
        return idnd;
    }

    public void setIdnd(int idnd) {
        this.idnd = idnd;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
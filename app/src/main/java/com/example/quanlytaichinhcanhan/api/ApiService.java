package com.example.quanlytaichinhcanhan.api;

import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.example.quanlytaichinhcanhan.model.nguoidung;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("nguoidung/list")
    Call<ArrayList<nguoidung>> getNguoiDungs();

    @GET("danhmuc/list")
    Call<ArrayList<danhmuc>> getDanhMucs();

    @GET("hoatdong/list")
    Call<ArrayList<hoatdong>> getHoatDongs();

    //thêm sửa xoá hoạt động
    @POST("hoatdong/add")
    Call<hoatdong> setHoatDongs(@Body hoatdong hoatdong);

    @POST("hoatdong/update/{id}")
    Call<hoatdong> editHoatDong(@Body hoatdong hoatdong, @Path("id") int id);

    @POST("hoatdong/delete/{id}")
    Call<hoatdong> xoaHoatDong(@Path("id") int id);

    //thêm sửa xoá danh mục
    @POST("hoatdong/add")
    Call<hoatdong> addDanhmuc(@Body danhmuc danhmuc);

    @POST("hoatdong/update/{id}")
    Call<hoatdong> editDanhmuc(@Body danhmuc danhmuc, @Path("id") int id);

    @POST("hoatdong/delete/{id}")
    Call<hoatdong> xoaDanhmuc(@Path("id") int id);

    //thêm sửa xoá người dùng
    @POST("hoatdong/add")
    Call<hoatdong> addNguoidung(@Body nguoidung nguoidung);

    @POST("hoatdong/update/{id}")
    Call<hoatdong> editNguoidung(@Body nguoidung nguoidung, @Path("id") int id);

    @POST("hoatdong/delete/{id}")
    Call<hoatdong> xoaNguoidung(@Path("id") int id);
}

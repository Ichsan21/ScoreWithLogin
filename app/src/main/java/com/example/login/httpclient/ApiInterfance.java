package com.example.login.httpclient;


import com.example.login.model.ResponseLogin;
import com.example.login.model.ResponseRegister;
import com.example.login.model2.ResponseDetail;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterfance {

    @POST("register")
    @FormUrlEncoded
    Call<ResponseRegister> setRegister(
            @Field("name")
            String name,
            @Field("username")
            String username,
            @Field("email")
            String email,
            @Field("password")
            String password,
            @Field("c_password")
             String c_password

    );

    @POST("login")
    @FormUrlEncoded
    Call<ResponseLogin> setLogin(
            @Field("username")
                    String username,
            @Field("password")
                    String password

    );

    @POST("details")
    @Headers(("Accept:application/json"))
    Call<ResponseDetail> getDetails(
            @Header("Authorization")String token);

}

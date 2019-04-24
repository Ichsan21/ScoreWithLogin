package com.example.login.httpclient;


import com.example.login.model.ResponseAllLeague;
import com.example.login.model.ResponseDetailLeague;
import com.example.login.model.ResponseLogin;
import com.example.login.model.ResponseLookupTeam;
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

public interface ApiInterface {

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

    @GET("search_all_leagues.php?s=Soccer")
    Call<ResponseAllLeague> getAllLeague();

    @GET("eventspastleague.php?")
    Call<ResponseDetailLeague> getAllEvent(@Query("id") String id);

    @GET("lookupteam.php?")
    Call<ResponseLookupTeam> getLookupTeam(@Query("id") String id);


}

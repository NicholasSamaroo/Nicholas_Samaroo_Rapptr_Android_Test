package com.datechnologies.androidtest.api;

import com.datechnologies.androidtest.POJOs.Data;
import com.datechnologies.androidtest.POJOs.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatAPI {
    // http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
    @GET("Tests/scripts/chat_log.php")
    Call<Data> getChatBubbles();

    // http://dev.rapptrlabs.com/Tests/scripts/login.php
    @FormUrlEncoded
    @POST("Tests/scripts/login.php")
    Call<Post> post(@Field("email") String email, @Field("password") String password);

}

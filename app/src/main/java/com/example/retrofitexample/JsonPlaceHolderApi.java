package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    //A ? mark is a query in json payload
    @GET("posts")
    Call<List<Post>> getPostsQuery(@Query("userId") int userId);
    //Retrofit will make it look like the following /posts?userId=4

    @GET
    Call<Bus> getBus(@Url String url);


}

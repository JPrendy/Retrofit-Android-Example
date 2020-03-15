package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Comment {


    private int postId;

    private int id;

    private  String name;

    private String email;

//    In the json payload the field is actually body, but since we want to make it more readable we set it to text
//    https://jsonplaceholder.typicode.com/posts/2/comments
    @SerializedName("body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}

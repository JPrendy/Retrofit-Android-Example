package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //getPosts();
        //getComments();
        getBus();
        //getBus2();
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsQuery(4);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = " ";
                    content += "1" + post.getUserId() + "\n";
                    content += "2" + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>(){

            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments){
                    String content = "";
                    content+= "Text" + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }

    //the following gets a json object not json array, so we have to do the following https://data.smartdublin.ie/cgi-bin/rtpi/realtimebusinformation?stopid=1190
    //The error message tells you that while converting the json to a java object the call expected an array in the json but got an object instead.
    private void getBus(){
        Call<Bus> call = jsonPlaceHolderApi.getBus("https://data.smartdublin.ie/cgi-bin/rtpi/realtimebusinformation?stopid=1190");

        call.enqueue(new Callback<Bus>(){

            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                ArrayList<BusResult> ok = response.body().getResults();
                textViewResult.setText(ok.get(1).getArrivaldatetime());
            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }
}

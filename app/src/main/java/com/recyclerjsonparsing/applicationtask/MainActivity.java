package com.recyclerjsonparsing.applicationtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static RequestInterface requestInterface;
    RecyclerView recyclerView;
    List<Object> items;
    EditText etPost;
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestInterface = Controller.getApi();

        items = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(this, items);
        recyclerView.setAdapter(adapter);
        items.add(0, new Post());
        items.add(1, new Comment());
//        items.add(2, new User());

            requestInterface.getUser(3).enqueue(new Callback <User>() {
            @Override
            public void onResponse(Call <User> call, Response <User> response) {
                recyclerView.getAdapter().notifyDataSetChanged();
                items.add(2, response.body());
            }

            @Override
            public void onFailure(Call <User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

        requestInterface.getPhoto(3).enqueue(new Callback <Photo>() {
            @Override
            public void onResponse(Call <Photo> call, Response <Photo> response) {
                recyclerView.getAdapter().notifyDataSetChanged();
                items.add(2, response.body());
            }

            @Override
            public void onFailure(Call <Photo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });


        Random random = new Random();
        int n = random.nextInt(200);
        requestInterface.getTodo(n).enqueue(new Callback <Todo>() {
            @Override
            public void onResponse(Call <Todo> call, Response <Todo> response) {
                recyclerView.getAdapter().notifyDataSetChanged();
                items.add(3, response.body());
            }

            @Override
            public void onFailure(Call <Todo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickButton(View v) {
        etPost = (EditText) findViewById(R.id.etPost);
        int n = Integer.parseInt(etPost.getText().toString());
        if((n > 0) && (n <= 100)) {
            requestInterface.getData(n).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    items.remove(0);
                    items.add(0, response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onClickComment(View v) {
        etComment = (EditText) findViewById(R.id.etComment);
        int n = Integer.parseInt(etComment.getText().toString());
        if((n > 0) && (n <= 500)) {
            requestInterface.getComment(n).enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    items.remove(1);
                    items.add(1, response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

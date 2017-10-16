package com.recyclerjsonparsing.applicationtask;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("/posts/{n}")
    Call <Post> getData(@Path("n") int n);

    @GET("/comments/{n}")
    Call <Comment> getComment(@Path("n") int n);

    @GET("/users")
    Call <User> getUser(@Query("id") int... ids);

    @GET("/photos/{n}")
    Call<Photo> getPhoto(@Path("n") int n);

    @GET("/todos/{n}")
    Call <Todo> getTodo(@Path("n") int n);



}

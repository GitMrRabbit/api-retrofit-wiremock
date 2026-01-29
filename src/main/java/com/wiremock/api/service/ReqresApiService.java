package com.wiremock.api.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.wiremock.api.model.request.CreateUserRequest;
import com.wiremock.api.model.response.CreateUserResponse;
import com.wiremock.api.model.request.UpdateUserRequest;
import com.wiremock.api.model.response.UpdateUserResponse;
import com.wiremock.api.model.response.UserResponse;
import com.wiremock.api.model.response.UsersResponse;

public interface ReqresApiService {

    @GET("users")
    Call<UsersResponse> getUsers(@Query("page") int page);

    @GET("users/{id}")
    Call<UserResponse> getUser(@Path("id") int id);

    @POST("users")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest request);

    @PUT("users/{id}")
    Call<UpdateUserResponse> updateUser(@Path("id") int id, @Body UpdateUserRequest request);

    @PATCH("users/{id}")
    Call<UpdateUserResponse> patchUser(@Path("id") int id, @Body UpdateUserRequest request);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}

package com.example.projecttemplateexample

import com.example.projecttemplateexample.models.PostDto
import com.example.projecttemplateexample.models.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): UserDto?

    @GET("users")
    suspend fun getUsers() : List<UserDto>

    @GET("posts")
    suspend fun getPosts() : List<PostDto>
}
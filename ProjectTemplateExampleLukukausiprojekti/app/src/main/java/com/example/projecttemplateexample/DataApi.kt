package com.example.projecttemplateexample

import com.example.projecttemplateexample.models.PostDto
import com.example.projecttemplateexample.models.UserDto
import retrofit2.http.GET

interface DataApi {
    @GET("users")
    suspend fun getUsers() : List<UserDto>

    @GET("posts")
    suspend fun getPosts() : List<PostDto>
}
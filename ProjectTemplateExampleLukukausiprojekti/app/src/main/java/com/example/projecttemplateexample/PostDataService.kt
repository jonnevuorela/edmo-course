package com.example.projecttemplateexample

import com.example.projecttemplateexample.models.PostDto

interface PostDataService {
    suspend fun getPosts() : List<PostDto>
}
package com.example.projecttemplateexample

import com.example.projecttemplateexample.models.PostDto

class PostDataServieImpl(private val api: DataApi) : PostDataService {
    override suspend fun getPosts(): List<PostDto> {
        return api.getPosts()
    }

}
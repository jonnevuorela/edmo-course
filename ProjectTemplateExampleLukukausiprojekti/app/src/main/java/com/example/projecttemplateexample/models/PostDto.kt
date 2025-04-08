package com.example.projecttemplateexample.models

import java.nio.file.FileSystemAlreadyExistsException

data class PostDto(val userId: Int, val id: Int , val title: String, val body: String)

data class PostState(
    val loading: Boolean = false,
    val posts: List<PostDto> = emptyList(),
    val error: String? = null,
)


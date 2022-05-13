package com.example.cloud

import com.example.model.PostList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts():Response<PostList>


}
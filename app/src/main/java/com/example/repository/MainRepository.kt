package com.example.repository

import androidx.lifecycle.MutableLiveData
import com.example.cloud.ApiService
import com.example.model.PostList

class MainRepository(private val apiService: ApiService) {

    private var postMutableLiveData: MutableLiveData<Response<PostList>> = MutableLiveData()

    val posLiveData: MutableLiveData<Response<PostList>>
        get() = postMutableLiveData

    suspend fun getAllPosts() {
        try {
            val result = apiService.getAllPosts()
            result.body()?.let {
                postMutableLiveData.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            postMutableLiveData.postValue(Response.Error(e.message.toString()))
        }

    }

}

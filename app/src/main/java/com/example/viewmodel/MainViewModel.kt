package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.PostList
import com.example.repository.MainRepository
import com.example.repository.Response
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            mainRepository.getAllPosts()
        }
    }

    val posts: LiveData<Response<PostList>>
        get() = mainRepository.posLiveData
}
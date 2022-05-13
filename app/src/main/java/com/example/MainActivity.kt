package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.RecyclerAdapter
import com.example.cloud.ApiService
import com.example.cloud.RetrofitBuilder
import com.example.databinding.ActivityMainBinding
import com.example.repository.MainRepository
import com.example.repository.Response
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val Any.TAG: String get() = this::class.java.simpleName
    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val apiService = RetrofitBuilder.getInstance().create(ApiService::class.java)
        val mainRepository = MainRepository(apiService)
        mainViewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(mainRepository)
            ).get(MainViewModel::class.java)

        mainViewModel.posts.observe(this, Observer {

            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE;
//                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE;

                    val data = mainViewModel.posts.value?.data
                    data?.let {
                        val adapter = RecyclerAdapter(data, this)
                        binding.recyclerView.layoutManager = LinearLayoutManager(this)
                        binding.recyclerView.adapter = adapter
//                            Log.d("TAG", "onCreate: ${mainViewModel.posts.value?.data.toString()}")
                    }

                }
                is Response.Error -> {

                }
            }

        })

    }
}
package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.id.text = intent.getStringExtra("ID")
        binding.title.text = intent.getStringExtra("TITLE")
        binding.body.text = intent.getStringExtra("BODY")

    }
}
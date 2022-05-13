package com.example.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.DetailActivity
import com.example.MainActivity
import com.example.databinding.PostItemBinding
import com.example.model.PostList

class RecyclerAdapter(private val postList: PostList, private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.PostItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PostItemViewHolder(
        PostItemBinding.inflate(
            LayoutInflater.from(context), viewGroup, false
        ).root
    )

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        PostItemBinding.bind(holder.itemView).apply {
            id.text = postList[position].id.toString()
            title.text = postList[position].title.toString()

            cardView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("ID", postList[position].id.toString())
                intent.putExtra("TITLE", postList[position].title)
                intent.putExtra("BODY", postList[position].body)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size

    }

    class PostItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view)
}
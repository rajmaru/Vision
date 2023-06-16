package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.Top10ItemBinding
import com.one.vision.models.Movie

class Top10Adapter : RecyclerView.Adapter<Top10Adapter.Top10ViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10ViewHolder {
        return Top10ViewHolder(Top10ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Top10ViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.top10PrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.top10PrimeIconLayout.visibility = View.GONE
        }
        holder.binding.top10Number.text = "${position + 1}"
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.top10Image)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class Top10ViewHolder(val binding: Top10ItemBinding) : RecyclerView.ViewHolder(binding.root)

}
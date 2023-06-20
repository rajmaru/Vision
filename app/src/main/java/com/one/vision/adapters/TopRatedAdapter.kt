package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.TopRatedItemBinding
import com.one.vision.models.Movie

class TopRatedAdapter  : RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder(TopRatedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.topRatedPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.topRatedPrimeIconLayout.visibility = View.GONE
        }
        holder.binding.topRatedRatingsTv.text = moviesList[position].rating
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.topRatedImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class TopRatedViewHolder(val binding: TopRatedItemBinding) : RecyclerView.ViewHolder(binding.root)

}
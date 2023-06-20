package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.PopularMoviesItemBinding
import com.one.vision.models.Movie

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.popularMoviesPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.popularMoviesPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.popularMoviesImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class PopularMoviesViewHolder(val binding: PopularMoviesItemBinding) : RecyclerView.ViewHolder(binding.root)

}
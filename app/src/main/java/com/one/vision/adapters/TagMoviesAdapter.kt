package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.TagMoviesCardBinding
import com.one.vision.models.Movie

class TagMoviesAdapter  : RecyclerView.Adapter<TagMoviesAdapter.TagMoviesViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagMoviesViewHolder {
        return TagMoviesViewHolder(TagMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagMoviesViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.tagCardImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class TagMoviesViewHolder(val binding: TagMoviesCardBinding) : RecyclerView.ViewHolder(binding.root)

}
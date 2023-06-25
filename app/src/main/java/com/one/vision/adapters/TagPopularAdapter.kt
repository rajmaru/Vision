package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.TagMoviesCardBinding
import com.one.vision.models.Movie

class TagPopularAdapter  : RecyclerView.Adapter<TagPopularAdapter.TagPopularViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagPopularViewHolder {
        return TagPopularViewHolder(TagMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagPopularViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].poster)
            .into(holder.binding.tagCardImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class TagPopularViewHolder(val binding: TagMoviesCardBinding) : RecyclerView.ViewHolder(binding.root)

}
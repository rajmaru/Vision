package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.MovieCardBinding
import com.one.vision.databinding.OttMoviesCardBinding
import com.one.vision.models.Movie

class OttPopularAdapter  : RecyclerView.Adapter<OttPopularAdapter.OttPopularViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OttPopularViewHolder {
        return OttPopularViewHolder(OttMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OttPopularViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.ottMovieCardPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.ottMovieCardPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.ottCardImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class OttPopularViewHolder(val binding: OttMoviesCardBinding) : RecyclerView.ViewHolder(binding.root)

}
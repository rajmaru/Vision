package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.PopularSeriesItemBinding
import com.one.vision.models.Movie

class PopularSeriesAdapter  : RecyclerView.Adapter<PopularSeriesAdapter.PopularSeriesViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSeriesViewHolder {
        return PopularSeriesViewHolder(PopularSeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularSeriesViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.popularSeriesPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.popularSeriesPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.popularSeriesImage)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class PopularSeriesViewHolder(val binding: PopularSeriesItemBinding) : RecyclerView.ViewHolder(binding.root)

}
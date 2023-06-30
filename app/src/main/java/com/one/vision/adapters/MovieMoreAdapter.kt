package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.Top10ItemBinding
import com.one.vision.models.Movie

class MovieMoreAdapter   : RecyclerView.Adapter<MovieMoreAdapter.MovieMoreViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieMoreViewHolder {
        return MovieMoreViewHolder(Top10ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieMoreViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.top10PrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.top10PrimeIconLayout.visibility = View.GONE
        }
        holder.binding.top10Number.visibility = View.GONE
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.top10Image)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MovieMoreViewHolder(val binding: Top10ItemBinding) : RecyclerView.ViewHolder(binding.root)

}
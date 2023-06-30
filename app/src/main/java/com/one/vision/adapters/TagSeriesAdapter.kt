package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.TagMoviesCardBinding
import com.one.vision.models.Movie

class TagSeriesAdapter  : RecyclerView.Adapter<TagSeriesAdapter.TagSeriesViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>
    var onClickItem: ((Movie) -> Unit)? = null

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagSeriesViewHolder {
        return TagSeriesViewHolder(TagMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagSeriesViewHolder, position: Int) {
        if(moviesList[position].isPrime!!){
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.tagMovieCardPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].image)
            .into(holder.binding.tagCardImage)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class TagSeriesViewHolder(val binding: TagMoviesCardBinding) : RecyclerView.ViewHolder(binding.root)

}
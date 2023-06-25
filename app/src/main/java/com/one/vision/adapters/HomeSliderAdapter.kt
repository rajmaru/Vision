package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.HomeSliderItemBinding
import com.one.vision.models.Movie

class HomeSliderAdapter : RecyclerView.Adapter<HomeSliderAdapter.HomeSliderViewHolder>(){
    private lateinit var context: Context
    private lateinit var moviesList: ArrayList<Movie>
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMoviesList(context: Context, moviesList: ArrayList<Movie>){
        this.context = context
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        return HomeSliderViewHolder(HomeSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        holder.binding.homeSliderTitle.text = moviesList[position].title
        if(moviesList[position].isPrime!!){
            holder.binding.homeSliderPrimeIconLayout.visibility = View.VISIBLE
        }else{
            holder.binding.homeSliderPrimeIconLayout.visibility = View.GONE
        }
        Glide.with(context.applicationContext)
            .load(moviesList[position].poster)
            .into(holder.binding.homeSliderImage)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class HomeSliderViewHolder(val binding: HomeSliderItemBinding) : RecyclerView.ViewHolder(binding.root)

}
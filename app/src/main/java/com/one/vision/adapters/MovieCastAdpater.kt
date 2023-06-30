package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.MovieCastItemBinding
import com.one.vision.models.Cast

class MovieCastAdpater : RecyclerView.Adapter<MovieCastAdpater.MovieCastViewHolder>() {
    private lateinit var context: Context
    private lateinit var castList: ArrayList<Cast>

    fun setCastList(context: Context, castList: ArrayList<Cast>) {
        this.context = context
        this.castList = castList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        return MovieCastViewHolder(MovieCastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        Glide.with(context.applicationContext)
            .load(castList[position]?.image)
            .into(holder.binding.movieCastImg)
        holder.binding.movieCastTv.text = castList[position]?.name
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    inner class MovieCastViewHolder(val binding: MovieCastItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
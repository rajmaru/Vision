package com.one.vision.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.one.vision.databinding.MovieSeasonItemBinding

class MovieSeasonAdapter(private val season: List<String>) : RecyclerView.Adapter<MovieSeasonAdapter.MovieSeasonViewHolder>() {

    var position: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeasonViewHolder {
        return MovieSeasonViewHolder(MovieSeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieSeasonViewHolder, position: Int) {
        holder.binding.seasonTv.text = season[position]
        this.position = position
    }

    override fun getItemCount(): Int {
        return season.size
    }

    inner class MovieSeasonViewHolder(val binding: MovieSeasonItemBinding) : RecyclerView.ViewHolder(binding.root)
}


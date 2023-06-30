package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.MovieSeasonItemBinding
import com.one.vision.models.Episode

class MovieSeasonRVAdapter : RecyclerView.Adapter<MovieSeasonRVAdapter.MovieSeasonRVViewHolder>() {

    private lateinit var context: Context
    private lateinit var episodes: ArrayList<Episode>

    fun setEpisodesList(context: Context, episodes: ArrayList<Episode>){
        this.context = context
        this.episodes = episodes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeasonRVViewHolder {
        return MovieSeasonRVViewHolder(MovieSeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieSeasonRVViewHolder, position: Int) {
        val episode = episodes[position]
        if(episode != null){
            holder.binding.movieSeasonTitle.text = episode.title
            val subtitle = "${episode.number} • ${episode.date} • ${episode.duration}"
            holder.binding.movieSeasonSubtitle.text = subtitle
            Glide.with(context)
                .load(episode.image)
                .into(holder.binding.movieSeasonImg)
        }
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class MovieSeasonRVViewHolder(val binding: MovieSeasonItemBinding) : RecyclerView.ViewHolder(binding.root)
}
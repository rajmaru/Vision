package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one.vision.databinding.SeasonViewpagerItemBinding
import com.one.vision.models.Season

class MovieSeasonPagerAdapter : RecyclerView.Adapter<MovieSeasonPagerAdapter.MovieSeasonViewHolder>() {

    private var rvAdapter= MovieSeasonRVAdapter()
    private lateinit var context: Context
    private lateinit var seasons: ArrayList<Season>
    fun setSeasonsList(context: Context, seasons: ArrayList<Season>){
        this.context = context
        this.seasons = seasons
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeasonViewHolder {
        return MovieSeasonViewHolder(SeasonViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieSeasonViewHolder, position: Int) {
        rvAdapter.setEpisodesList(context, seasons[position].episodesList!!)
        holder.binding.movieSeasonRv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class MovieSeasonViewHolder(val binding: SeasonViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root)
}


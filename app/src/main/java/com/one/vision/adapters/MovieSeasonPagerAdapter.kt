package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one.vision.databinding.SeasonViewpagerItemBinding
import com.one.vision.models.Season

class MovieSeasonPagerAdapter :
    RecyclerView.Adapter<MovieSeasonPagerAdapter.MovieSeasonViewHolder>() {

    private var rvAdapter = MovieSeasonRVAdapter()
    private lateinit var context: Context
    private lateinit var seasons: ArrayList<Season>
    private var rv: RecyclerView? = null
    var showGradient: MutableLiveData<Boolean> = MutableLiveData()

    fun setSeasonsList(context: Context, seasons: ArrayList<Season>) {
        this.context = context
        this.seasons = seasons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeasonViewHolder {
        return MovieSeasonViewHolder(
            SeasonViewpagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieSeasonViewHolder, position: Int) {
        rvAdapter.setEpisodesList(context, seasons[position].episodesList!!)
        rv = holder.binding.movieSeasonRv
        rv?.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && dy > 0) {
                        //scrolled to BOTTOM
                        showGradient.postValue(false)
                    } else {
                        showGradient.postValue(true)
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return seasons.size
    }


    inner class MovieSeasonViewHolder(val binding: SeasonViewpagerItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

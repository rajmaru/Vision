package com.one.vision.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.one.vision.R
import com.one.vision.adapters.TagMoviesAdapter
import com.one.vision.adapters.TagPopularAdapter
import com.one.vision.adapters.TagSeriesAdapter
import com.one.vision.databinding.ActivityTagBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie
import com.one.vision.models.Tag


class TagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTagBinding

    private var tag: String? = null
    private var isToolbarShown = false

    private var popularList = ArrayList<Movie>()
    private var moviesList = ArrayList<Movie>()
    private var seriesList = ArrayList<Movie>()

    private var movieTagsList = ArrayList<String?>()
    private var movieLanguagesList = ArrayList<String?>()
    private var movieCastsList = ArrayList<Tag?>()
    private lateinit var movie: Movie

    private lateinit var popularAdapter: TagPopularAdapter
    private lateinit var moviesAdapter: TagMoviesAdapter
    private lateinit var seriesAdapter: TagSeriesAdapter

    private lateinit var customItemMargin: CustomItemMargin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusBarTransparent()
        init()
        getTag()
        getPopularData()
        getMoviesData()
        getSeriesData()
        onClick()

    }

    private fun getTag() {
        tag = intent.getStringExtra("TAG")
        Log.d("RAJ", tag.toString())
        setTag()
    }

    private fun setTag() {
        if (tag.equals("Disney+ Hotstar")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_disney_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#01013D"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#0052B8"))
        }
        if (tag.equals("Prime Video")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_primevideo_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#002664"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#1AA7D5"))
        }
        if (tag.equals("Netflix")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_netflix_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Zee5")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_zee5_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Alt Balaji")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_altbalaji_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Voot")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_voot_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#390065"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#7700D4"))
        }
        if (tag.equals("Jio Cinema")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_jiocinema_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#7C0053"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#D9008D"))
        }
        if (tag.equals("Sony Liv")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_sonyliv_logo)
            binding.tagToolbarTitle.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("English")) {
            binding.tagToolbarTitle.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbarTitle.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Hindi")) {
            binding.tagToolbarTitle.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbarTitle.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Marathi")) {
            binding.tagToolbarTitle.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbarTitle.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Gujarati")) {
            binding.tagToolbarTitle.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbarTitle.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
    }

    private fun onScrollingHideToolbar() {
        binding.tagToolbarTitle.alpha = 0f
        binding.tagNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("SCROLL_VIEW", scrollY.toString())
            if (scrollY > 180) {
                // Scrolled down
                if (isToolbarShown) {
                    binding.tagToolbarTitle.animate()
                        .alpha(1f)
                        .setDuration(500) // Set the duration of the animation in milliseconds
                        .start()
                    binding.tagLogo.animate()
                        .alpha(0f)
                        .setDuration(500) // Set the duration of the animation in milliseconds
                        .start()
                    isToolbarShown = false
                }
            } else if (scrollY < 180) {
                // Scrolled up
                if (!isToolbarShown) {
                    binding.tagToolbarTitle.animate()
                        .alpha(0f)
                        .setDuration(500) // Set the duration of the animation in milliseconds
                        .start()
                    binding.tagLogo.animate()
                        .alpha(1f)
                        .setDuration(500) // Set the duration of the animation in milliseconds
                        .start()
                    isToolbarShown = true
                }
            }
        }
    }

    private fun init() {
        popularAdapter = TagPopularAdapter()
        moviesAdapter = TagMoviesAdapter()
        seriesAdapter = TagSeriesAdapter()
        customItemMargin = CustomItemMargin()
        movieLanguagesList.apply {
            add("English")
            add("Hindi")
        }
        movieCastsList.apply {
            add(Tag("Shawn Ashmore",R.drawable.sample_cast_img1))
            add(Tag("Shawn Ashmore",R.drawable.sample_cast_img1))
            add(Tag("Shawn Ashmore",R.drawable.sample_cast_img1))
        }
        movie =  Movie(
            "0001",
            "https://m.media-amazon.com/images/I/91A9U++FKnL._AC_SL1500_.jpg",
            "Aftermath",
            "4.5",
            "2014",
            "2h 30m",
            movieTagsList,
            "A young couple struggling to stay together, when they are offered an amazing deal on a home with a questionable past that would normally be beyond their means. In a final attempt to start fresh as a couple they take the deal.",
            movieLanguagesList,
            movieCastsList,
            "Disney",
            true
        )
    }

    private fun onClick() {
        binding.tagBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getPopularData() {
        popularList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setPopularList()
    }

    private fun setPopularList() {
        popularAdapter.setMoviesList(this, popularList)
        binding.tagPopularRv.apply {
            addItemDecoration(customItemMargin)
            adapter = popularAdapter
            layoutManager =
                LinearLayoutManager(this@TagActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getMoviesData() {
        moviesList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setMoviesList()
    }

    private fun setMoviesList() {
        moviesAdapter.setMoviesList(this, moviesList)
        binding.tagMoviesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = moviesAdapter
            layoutManager =
                LinearLayoutManager(this@TagActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getSeriesData() {
        seriesList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setSeriesList()
    }

    private fun setSeriesList() {
        seriesAdapter.setMoviesList(this, seriesList)
        binding.tagSeriesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = seriesAdapter
            layoutManager =
                LinearLayoutManager(this@TagActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun statusBarTransparent() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}
package com.one.vision

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
import com.one.vision.adapters.TagMoviesAdapter
import com.one.vision.adapters.TagPopularAdapter
import com.one.vision.adapters.TagSeriesAdapter
import com.one.vision.databinding.ActivityTagBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie


class TagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTagBinding

    private var tag: String? = null
    private var isToolbarShown = false

    private var popularList = ArrayList<Movie>()
    private var moviesList = ArrayList<Movie>()
    private var seriesList = ArrayList<Movie>()

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
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#01013D"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#0052B8"))
        }
        if (tag.equals("Prime Video")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_primevideo_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#002664"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#1AA7D5"))
        }
        if (tag.equals("Netflix")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_netflix_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Zee5")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_zee5_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Alt Balaji")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_altbalaji_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("Voot")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_voot_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#390065"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#7700D4"))
        }
        if (tag.equals("Jio Cinema")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_jiocinema_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#7C0053"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#D9008D"))
        }
        if (tag.equals("Sony Liv")) {
            onScrollingHideToolbar()
            binding.tagLogo.setImageResource(R.drawable.tag_sonyliv_logo)
            binding.tagToolbar.text = tag
            binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
            binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
        }
        if (tag.equals("English")) {
            binding.tagToolbar.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbar.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Hindi")) {
            binding.tagToolbar.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbar.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Marathi")) {
            binding.tagToolbar.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbar.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
        if (tag.equals("Gujarati")) {
            binding.tagToolbar.alpha = 1f
            binding.tagBackgroundGradient.visibility = View.GONE
            binding.tagLogo.visibility = View.GONE
            val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 270
            binding.tagPopularTitle.layoutParams = layoutParams
            binding.tagToolbar.text = tag
            binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
            binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
            binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
        }
    }

    private fun onScrollingHideToolbar() {
        binding.tagToolbar.alpha = 0f
        binding.tagNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("SCROLL_VIEW", scrollY.toString())
            if (scrollY > 180) {
                // Scrolled down
                if (isToolbarShown) {
                    binding.tagToolbar.animate()
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
                    binding.tagToolbar.animate()
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
    }

    private fun onClick() {
        binding.tagBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getPopularData() {
        popularList.apply {
            add(
                Movie(
                    "https://i.pinimg.com/564x/c2/9a/a4/c29aa4496a466b5ef53051ca4abd0b35.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/ee/f5/9b/eef59b8051f1474aa779d8da3eb33f64.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/29/bf/15/29bf1527dfa7fdd1221d7e9d1c170a7f.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/6c/78/16/6c7816bd8a81806059ffe384623dd075.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/e3/6a/21/e36a212060c30dfc78671ed604cbdc8d.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/33/a2/fe/33a2feaaeb0391e0d49e849b791c52b0.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/95/8d/43/958d43df5f0841c4a75f10649205bb54.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/40/02/f8/4002f81ecb32d46779b18209bcde10ff.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/7f/1a/52/7f1a520c2fa87f084b0748ea4d7006e2.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/5b/e1/2e/5be12e6b60336095526649c536a4cc9c.jpg",
                    null,
                    null,
                    false
                )
            )

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
            add(
                Movie(
                    "https://i.pinimg.com/564x/c2/9a/a4/c29aa4496a466b5ef53051ca4abd0b35.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/ee/f5/9b/eef59b8051f1474aa779d8da3eb33f64.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/29/bf/15/29bf1527dfa7fdd1221d7e9d1c170a7f.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/6c/78/16/6c7816bd8a81806059ffe384623dd075.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/e3/6a/21/e36a212060c30dfc78671ed604cbdc8d.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/33/a2/fe/33a2feaaeb0391e0d49e849b791c52b0.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/95/8d/43/958d43df5f0841c4a75f10649205bb54.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/40/02/f8/4002f81ecb32d46779b18209bcde10ff.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/7f/1a/52/7f1a520c2fa87f084b0748ea4d7006e2.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/5b/e1/2e/5be12e6b60336095526649c536a4cc9c.jpg",
                    null,
                    null,
                    false
                )
            )

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
            add(
                Movie(
                    "https://i.pinimg.com/564x/c2/9a/a4/c29aa4496a466b5ef53051ca4abd0b35.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/ee/f5/9b/eef59b8051f1474aa779d8da3eb33f64.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/29/bf/15/29bf1527dfa7fdd1221d7e9d1c170a7f.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/6c/78/16/6c7816bd8a81806059ffe384623dd075.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/e3/6a/21/e36a212060c30dfc78671ed604cbdc8d.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/33/a2/fe/33a2feaaeb0391e0d49e849b791c52b0.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/95/8d/43/958d43df5f0841c4a75f10649205bb54.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/40/02/f8/4002f81ecb32d46779b18209bcde10ff.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/7f/1a/52/7f1a520c2fa87f084b0748ea4d7006e2.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/5b/e1/2e/5be12e6b60336095526649c536a4cc9c.jpg",
                    null,
                    null,
                    false
                )
            )

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
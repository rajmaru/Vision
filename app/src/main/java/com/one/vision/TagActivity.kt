package com.one.vision

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.one.vision.adapters.TagMoviesAdapter
import com.one.vision.adapters.TagPopularAdapter
import com.one.vision.adapters.TagSeriesAdapter
import com.one.vision.databinding.ActivityTagBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie


class TagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTagBinding

    private var previousScrollY = 0

    private var popularList= ArrayList<Movie>()
    private var moviesList= ArrayList<Movie>()
    private var seriesList= ArrayList<Movie>()

    private lateinit var popularAdapter: TagPopularAdapter
    private lateinit var moviesAdapter: TagMoviesAdapter
    private lateinit var seriesAdapter: TagSeriesAdapter

    private lateinit var customItemMargin: CustomItemMargin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusBarTransparent()
        onScrollingHideToolbar()
        init()
        getPopularData()
        getMoviesData()
        getSeriesData()
        onClick()
    }

    private fun onScrollingHideToolbar() {
        binding.tagNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val deltaY = scrollY - previousScrollY
            previousScrollY = scrollY

            if (deltaY > 0) {
                // Scrolled down
                binding.tagToolbar.animate().translationY(0f).start()
            } else if (deltaY < 0) {
                // Scrolled up
                binding.tagToolbar.animate().translationY(-binding.tagToolbar.height.toFloat()).start()
            }
        }
    }

    private fun init(){
        popularAdapter = TagPopularAdapter()
        moviesAdapter = TagMoviesAdapter()
        seriesAdapter = TagSeriesAdapter()
        customItemMargin = CustomItemMargin()
    }

    private fun onClick(){
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
        overridePendingTransition(R.anim.slide_in_left,
            R.anim.slide_out_right)
    }
}
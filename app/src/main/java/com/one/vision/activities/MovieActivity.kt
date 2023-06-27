package com.one.vision.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.one.vision.R
import com.one.vision.databinding.ActivityMovieBinding
import com.one.vision.models.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private var movie: Movie? = null
    private var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusBarTransparent()
        onScrollingHideToolbar()
        init()
        getMovieData()
    }

    private fun onScrollingHideToolbar() {
        binding.movieToolbarTitle.alpha = 0f
        binding.movieNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("SCROLL_VIEW", scrollY.toString())
            // Smooth scroll up
            if (scrollY in 315..450) {
                binding.movieNestedScrollview.smoothScrollTo(0,728)
                binding.movieToolbarTitle.animate()
                    .alpha(1f)
                    .setDuration(100)
                    .start()
                binding.moviePlayBtn.animate()
                    .alpha(0f)
                    .setDuration(10)
                    .start()
                binding.moviePosterImg.animate()
                    .alpha(0f)
                    .setDuration(500)
                    .start()
            }
            // Smooth scroll down
            if (scrollY in 500..600) {
                binding.movieNestedScrollview.smoothScrollTo(0,0)
                binding.movieToolbarTitle.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .start()
                binding.moviePlayBtn.animate()
                    .alpha(1f)
                    .setDuration(10)
                    .start()
                binding.moviePosterImg.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .start()
            }
        }
    }

    private fun init() {
    }

    private fun getMovieData() {
        movie = intent.getParcelableExtra("MOVIE")
        setMovieData()
    }

    private fun setMovieData() {
        binding.movieToolbarTitle.text = movie?.title
        binding.movieTitleTv.text = movie?.title
        binding.movieRatingsTv.text = movie?.rating
        setMovieTag()
        binding.movieDescTv.text = movie?.description
    }

    private fun setMovieTag() {
        tag = movie?.year + " | " + movie?.duration
        movie?.tags.let { tagsList ->
            for (i in tagsList!!) {
                tag += " | $i"
            }
        }
        binding.movieTagsTv.text = tag
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
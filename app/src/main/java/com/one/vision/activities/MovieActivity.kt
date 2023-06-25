package com.one.vision.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.one.vision.R
import com.one.vision.databinding.ActivityMovieBinding
import com.one.vision.models.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusBarTransparent()
        getMovieData()
    }

    private fun getMovieData() {
        movie = intent.getParcelableExtra("MOVIE")
        setMovieData()
    }

    private fun setMovieData() {
        binding.movieToolbarTitle.text = movie?.title
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
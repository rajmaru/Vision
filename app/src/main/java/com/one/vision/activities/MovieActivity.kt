package com.one.vision.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.one.vision.R
import com.one.vision.adapters.MovieCastAdpater
import com.one.vision.adapters.MovieMoreAdapter
import com.one.vision.adapters.MovieRecommendedAdapter
import com.one.vision.databinding.ActivityMovieBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private var movie: Movie? = null
    private var subtitle: String? = null
    private var languages: String? = null

    private var recommendedMovieList = ArrayList<Movie>()
    private var moreLikeThisMovieList = ArrayList<Movie>()

    private lateinit var castAdapter: MovieCastAdpater
    private lateinit var recommendedAdapter: MovieRecommendedAdapter
    private lateinit var moreLikeThisAdapter: MovieMoreAdapter
    private lateinit var customItemMargin: CustomItemMargin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusBarTransparent()
        onScrollingHideToolbar()
        init()
        getMovieData()
        onClick()
    }

    private fun statusBarTransparent() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun init() {
        castAdapter = MovieCastAdpater()
        recommendedAdapter = MovieRecommendedAdapter()
        moreLikeThisAdapter = MovieMoreAdapter()
        customItemMargin = CustomItemMargin()
    }

    private fun onScrollingHideToolbar() {
        binding.movieToolbarTitle.alpha = 0f
        binding.movieNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("SCROLL_VIEW", scrollY.toString())
            // Smooth scroll up
            if (scrollY in 315..450) {
                binding.movieNestedScrollview.smoothScrollTo(0, 728)
                binding.movieToolbarTitle.animate()
                    .alpha(1f)
                    .setDuration(100)
                    .start()
                binding.moviePlayBtn.animate()
                    .alpha(0f)
                    .setDuration(0)
                    .start()
                binding.moviePrimeIconLayout.animate()
                    .alpha(0f)
                    .setDuration(0)
                    .start()
            }
            // Smooth scroll down
            if (scrollY in 500..600) {
                binding.movieNestedScrollview.smoothScrollTo(0, 0)
                binding.movieToolbarTitle.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .start()
                binding.moviePlayBtn.animate()
                    .alpha(1f)
                    .setDuration(0)
                    .start()
                binding.moviePrimeIconLayout.animate()
                    .alpha(1f)
                    .setDuration(0)
                    .start()
            }
        }
    }

    private fun getMovieData() {
        movie = intent.getParcelableExtra("MOVIE")
        setMovieData()
    }

    private fun setMovieData() {
        binding.movieToolbarTitle.text = movie?.title
        binding.movieTitleTv.text = movie?.title
        binding.movieRatingsTv.text = movie?.rating
        setMovieSubtitle()
        setMovieLanguages()
        binding.movieDescTv.text = movie?.description
        setMovieCast()
        getMovieRecommended()
        getMoreLikeThisData()
    }

    private fun setMovieSubtitle() {
        subtitle = movie?.year + " • " + movie?.duration
        movie?.tags.let { languagesList ->
            for (i in languagesList!!.iterator()) {
                subtitle += " • $i"
            }
        }
        binding.movieSubtitleTv.text = subtitle
    }

    private fun setMovieLanguages() {
        movie?.languages.let { languagesList ->
            for (i in languagesList!!) {
                if (languages == null) {
                    languages = i
                } else {
                    languages += " | $i"
                }
            }
        }
        languages?.dropLast(2)
        binding.movieLanguagesTv.text = languages
    }

    private fun setMovieCast() {
        castAdapter.setCastList(this, movie?.Cast!!)
        binding.movieCastRv.apply {
            addItemDecoration(customItemMargin)
            adapter = castAdapter
            layoutManager =
                LinearLayoutManager(this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getMovieRecommended() {
        recommendedMovieList.apply {
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
        }
        setMovieRecommendedData()
    }

    private fun setMovieRecommendedData() {
        recommendedAdapter.setMoviesList(this, recommendedMovieList)
        binding.movieRecommendedRv.apply {
            addItemDecoration(customItemMargin)
            adapter = recommendedAdapter
            layoutManager =
                LinearLayoutManager(this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getMoreLikeThisData() {
        moreLikeThisMovieList.apply {
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
            add(movie!!)
        }
        setMoreLikeThisData()
    }

    private fun setMoreLikeThisData() {
        moreLikeThisAdapter.setMoviesList(this, moreLikeThisMovieList)
        binding.movieMoreLikeThisRv.apply {
            addItemDecoration(customItemMargin)
            adapter = moreLikeThisAdapter
            layoutManager =
                LinearLayoutManager(this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun onClick() {
        binding.movieBackButton.setOnClickListener {
            onBackPressed()
        }

        recommendedAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }

        moreLikeThisAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        outAnimation()
    }

    private fun redirectToMovieActivity(movie: Movie){
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra("MOVIE", movie)
        startActivity(intent)
        inAnimation()
    }

    private fun inAnimation() {
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    private fun outAnimation() {
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}
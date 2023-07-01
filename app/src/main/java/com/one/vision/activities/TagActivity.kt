package com.one.vision.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.one.vision.R
import com.one.vision.adapters.TagMoviesAdapter
import com.one.vision.adapters.TagPopularAdapter
import com.one.vision.adapters.TagSeriesAdapter
import com.one.vision.databinding.ActivityTagBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Cast
import com.one.vision.models.Movie
import com.one.vision.models.Episode
import com.one.vision.models.Season


class TagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTagBinding

    private var tag: String? = null
    private var isToolbarShown = false

    private var popularList = ArrayList<Movie>()
    private var moviesList = ArrayList<Movie>()
    private var seriesList = ArrayList<Movie>()

    private var movieTagsList = ArrayList<String>()
    private var movieLanguagesList = ArrayList<String>()
    private var movieCastsList = ArrayList<Cast>()
    private var movieSeasonsList = ArrayList<Season>()
    private var movieEpisodesList = ArrayList<Episode>()
    private lateinit var movie: Movie
    private lateinit var primeMovie: Movie
    private lateinit var series: Movie
    private lateinit var primeSeries: Movie

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

    private fun statusBarTransparent() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun init() {
        popularAdapter = TagPopularAdapter()
        moviesAdapter = TagMoviesAdapter()
        seriesAdapter = TagSeriesAdapter()
        customItemMargin = CustomItemMargin()
        //Dummy Movie Data
        movieTagsList.apply {
            add("Comedy")
            add("Drama")
            add("Action")
        }
        movieLanguagesList.apply {
            add("English")
            add("Hindi")
        }
        movieCastsList.apply {
            add(
                Cast(
                    "Shawn Ashmore",
                    "https://m.media-amazon.com/images/M/MV5BN2VjNGRjNDctYzg4MC00OTkwLThhMmItMWZlZGFiOTQ1YTAwXkEyXkFqcGdeQXVyNDAzNDk0MTQ@._V1_.jpg"
                )
            )
            add(
                Cast(
                    "Shawn Ashmore",
                    "https://m.media-amazon.com/images/M/MV5BN2VjNGRjNDctYzg4MC00OTkwLThhMmItMWZlZGFiOTQ1YTAwXkEyXkFqcGdeQXVyNDAzNDk0MTQ@._V1_.jpg"
                )
            )
            add(
                Cast(
                    "Shawn Ashmore",
                    "https://m.media-amazon.com/images/M/MV5BN2VjNGRjNDctYzg4MC00OTkwLThhMmItMWZlZGFiOTQ1YTAwXkEyXkFqcGdeQXVyNDAzNDk0MTQ@._V1_.jpg"
                )
            )
        }
        movieEpisodesList.apply {
            add(Episode(
                "1",
                "The Legend Begins",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3416/893416-h",
                "29 Jan 2021",
                "23m"
            ))
            add(Episode(
                "2",
                "The Monkey King",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3417/893417-h",
                "29 Jan 2021",
                "21m"
            ))
            add(Episode(
                "3",
                "Kishkindha Bound",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3419/893419-h",
                "29 Jan 2021",
                "21m"
            ))
            add(Episode(
                "4",
                "The Promise",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3421/893421-h",
                "29 Jan 2021",
                "23m"
            ))
            add(Episode(
                "5",
                "The Legend Begins",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3416/893416-h",
                "29 Jan 2021",
                "23m"
            ))
            add(Episode(
                "6",
                "The Monkey King",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3417/893417-h",
                "29 Jan 2021",
                "21m"
            ))
            add(Episode(
                "7",
                "Kishkindha Bound",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3419/893419-h",
                "29 Jan 2021",
                "21m"
            ))
            add(Episode(
                "8",
                "The Promise",
                "https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/3421/893421-h",
                "29 Jan 2021",
                "23m"
            ))
        }
        movieSeasonsList.apply {
            add(
                Season(
                    "Season 1",
                    movieEpisodesList
                )
            )
            add(Season(
                "Season 2",
                movieEpisodesList
            ))
            add(Season(
                "Season 3",
                movieEpisodesList
            ))
            add(Season(
                "Season 4",
                movieEpisodesList
            ))
            add(Season(
                "Season 5",
                movieEpisodesList
            ))
            add(Season(
                "Season 6",
                movieEpisodesList
            ))
        }
        movie = Movie(
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
            null,
            "Disney",
            false
        )
        primeMovie = Movie(
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
            null,
            "Disney",
            true
        )
        series = Movie(
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
            movieSeasonsList,
            "Disney",
            false
        )
        primeSeries = Movie(
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
            movieSeasonsList,
            "Disney",
            true
        )
    }

    private fun getTag() {
        tag = intent.getStringExtra("TAG")
        Log.d("TAG_NAME", tag.toString())
        setTag()
    }

    private fun setTag() {
        when (tag) {
            "Disney+ Hotstar" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_disney_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#01013D"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#0052B8"))
            }
            "Prime Video" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_primevideo_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#002664"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#1AA7D5"))
            }
            "Netflix" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_netflix_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
            }
            "Zee5"-> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_zee5_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
            }
            "Alt Balaji" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_altbalaji_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
            }
            "Sony Liv" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_sonyliv_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#000000"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#000000"))
            }

            "Voot" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_voot_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#390065"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#7700D4"))
            }
            "Jio Cinema" -> {
                onScrollingHideToolbar()
                binding.tagLogo.setImageResource(R.drawable.tag_jiocinema_logo)
                binding.tagBackgroundGradient.setColorFilter(Color.parseColor("#7C0053"))
                binding.tagRoot.setBackgroundColor(Color.parseColor("#D9008D"))
            }
            "English", "Hindi", "Marathi", "Gujarati" -> {
                binding.tagToolbarTitle.alpha = 1f
                binding.tagBackgroundGradient.visibility = View.GONE
                binding.tagLogo.visibility = View.GONE
                val layoutParams = binding.tagPopularTitle.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.topMargin = 270
                binding.tagPopularTitle.layoutParams = layoutParams
                binding.tagRoot.background = ContextCompat.getDrawable(this, R.color.background)
                binding.tagPopularTitle.setTextColor(resources.getColor(R.color.text_title))
                binding.tagMoviesTitle.setTextColor(resources.getColor(R.color.text_title))
                binding.tagSeriesTitle.setTextColor(resources.getColor(R.color.text_title))
            }
        }

        binding.tagToolbarTitle.text = tag
    }

    private fun onScrollingHideToolbar() {
        binding.tagToolbarTitle.alpha = 0f
        binding.tagNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("TAG_SCROLL_VIEW", scrollY.toString())
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

    private fun getPopularData() {
        popularList.apply {
            add(movie)
            add(series)
            add(primeMovie)
            add(series)
            add(primeSeries)
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
            add(series)
            add(series)
            add(series)
            add(series)
            add(series)
            add(series)
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

    private fun onClick() {
        binding.tagBackButton.setOnClickListener {
            onBackPressed()
        }

        popularAdapter.onClickItem =  {
            redirectToMovieActivity(it)
        }

        moviesAdapter.onClickItem =  {
            redirectToMovieActivity(it)
        }

        seriesAdapter.onClickItem =  {
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

    private fun inAnimation(){
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    private fun outAnimation(){
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}
package com.one.vision.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.one.vision.R
import com.one.vision.adapters.HomeSliderAdapter
import com.one.vision.adapters.LanguagesAdapter
import com.one.vision.adapters.OttAdapter
import com.one.vision.adapters.PopularMoviesAdapter
import com.one.vision.adapters.PopularSeriesAdapter
import com.one.vision.adapters.Top10Adapter
import com.one.vision.adapters.TopRatedAdapter
import com.one.vision.databinding.ActivityMainBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie
import com.one.vision.models.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var previousScrollY = 0

    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    private var sliderList = ArrayList<Movie>()
    private var top10List = ArrayList<Movie>()
    private var ottList = ArrayList<Tag>()
    private var topRatedList = ArrayList<Movie>()
    private var languagesList = ArrayList<Tag>()

    private var popularMoviesList = ArrayList<Movie>()
    private var popularSeriesList = ArrayList<Movie>()

    private var movieTagsList = ArrayList<String?>()
    private var movieLanguagesList = ArrayList<String?>()
    private var movieCastsList = ArrayList<Tag?>()
    private lateinit var movie: Movie

    private lateinit var homeSliderAdapter: HomeSliderAdapter
    private lateinit var top10Adapter: Top10Adapter
    private lateinit var ottAdapter: OttAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    private lateinit var languagesAdapter: LanguagesAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var popularSeriesAdapter: PopularSeriesAdapter

    private lateinit var customItemMargin: CustomItemMargin

    private var indicators = arrayOfNulls<ImageView>(0)
    private var indicatorsPosition = 0
    private var timer: Timer? = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onScrollingHideToolbar()
        init()
        getUserFromFirebase()
        getSliderData()
        setSliderIndicator()
        getOttData()
        getTop10Data()
        getTopRatedData()
        getLanguagesData()
        getPopularMoviesData()
        getPopularSeriesData()
        startAutoSliding()
        onClick()
    }

    private fun init() {
        homeSliderAdapter = HomeSliderAdapter()
        top10Adapter = Top10Adapter()
        ottAdapter = OttAdapter()
        topRatedAdapter = TopRatedAdapter()
        languagesAdapter = LanguagesAdapter()
        popularMoviesAdapter = PopularMoviesAdapter()
        popularSeriesAdapter = PopularSeriesAdapter()
        customItemMargin = CustomItemMargin()
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
            add(Tag("Shawn Ashmore", R.drawable.sample_cast_img1))
            add(Tag("Shawn Ashmore", R.drawable.sample_cast_img1))
            add(Tag("Shawn Ashmore", R.drawable.sample_cast_img1))
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
            "Disney",
            true
        )
    }

    private fun getUserFromFirebase() {
        auth = Firebase.auth
        user = auth!!.currentUser
        setProfilePic()
    }

    private fun setProfilePic() {
        if (user != null) {
            Glide.with(this)
                .load(user!!.photoUrl)
                .into(binding.homeProfilePic)
        }
    }

    private fun onScrollingHideToolbar() {
        binding.homeNestedScrollview.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val deltaY = scrollY - previousScrollY
            previousScrollY = scrollY

            if (deltaY > 50) {
                // Scrolled down
                binding.toolbar.animate().translationY(-binding.toolbar.height.toFloat()).start()
            } else if (deltaY < 0) {
                // Scrolled up
                binding.toolbar.animate().translationY(0f).start()
            }
        }
    }

    private fun getSliderData() {
        sliderList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setSliderData()
    }

    private fun setSliderData() {
        homeSliderAdapter.setMoviesList(this, sliderList)
        binding.homeSlider.adapter = homeSliderAdapter
    }

    private fun setSliderIndicator() {
        val indicatorsCount = binding.homeSlider.adapter?.itemCount ?: 0
        indicators = arrayOfNulls(indicatorsCount)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(10, 0, 10, 0)

        for (i in 0 until indicatorsCount) {
            indicators[i] = ImageView(this)
            indicators[i]?.layoutParams = layoutParams
            binding.homeSliderDotsLayout.addView(indicators[i])
        }
        changeSliderIndicator(indicatorsPosition)
    }

    private fun changeSliderIndicator(position: Int) {
        for (i in indicators.indices) {
            if (i == position) {
                indicators[i]?.setImageDrawable(getDrawable(R.drawable.home_slider_active_indicator))
            } else {
                indicators[i]?.setImageDrawable(getDrawable(R.drawable.home_slider_inactive_indicator))
            }
        }
    }

    private fun getOttData() {
        ottList.apply {
            add(Tag("Disney+ Hotstar", R.drawable.ic_disney_hotstar))
            add(Tag("Prime Video", R.drawable.ic_primevideo))
            add(Tag("Netflix", R.drawable.ic_netflix))
            add(Tag("Zee5", R.drawable.ic_zee5))
            add(Tag("Alt Balaji", R.drawable.ic_altbalaji))
            add(Tag("Voot", R.drawable.ic_voot))
            add(Tag("Jio Cinema", R.drawable.ic_jiocinema))
            add(Tag("Sony Liv", R.drawable.ic_sonyliv))
        }
        setOttData()
    }

    private fun setOttData() {
        ottAdapter.setOttList(this, ottList)
        binding.ottRv.apply {
            addItemDecoration(customItemMargin)
            adapter = ottAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getTop10Data() {
        top10List.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setTop10List()
    }

    private fun setTop10List() {
        top10Adapter.setMoviesList(this, top10List)
        binding.top10Rv.apply {
            addItemDecoration(customItemMargin)
            adapter = top10Adapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getTopRatedData() {
        topRatedList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setTopRatedList()
    }

    private fun setTopRatedList() {
        topRatedAdapter.setMoviesList(this, topRatedList)
        binding.topRatedRv.apply {
            addItemDecoration(customItemMargin)
            adapter = topRatedAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getLanguagesData() {
        languagesList.apply {
            add(Tag("English", R.drawable.language_eng))
            add(Tag("Hindi", R.drawable.language_hindi))
            add(Tag("Marathi", R.drawable.language_marathi))
            add(Tag("Gujarati", R.drawable.language_gujarati))
        }
        setLanguagesData()
    }

    private fun setLanguagesData() {
        languagesAdapter.setLanguagesList(this, languagesList)
        binding.languagesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = languagesAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getPopularMoviesData() {
        popularMoviesList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setPopularMoviesList()
    }

    private fun setPopularMoviesList() {
        popularMoviesAdapter.setMoviesList(this, popularMoviesList)
        binding.popularMoviesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = popularMoviesAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getPopularSeriesData() {
        popularSeriesList.apply {
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
            add(movie)
        }
        setPopularSeriesList()
    }

    private fun setPopularSeriesList() {
        popularSeriesAdapter.setMoviesList(this, popularSeriesList)
        binding.popularSeriesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = popularSeriesAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun startAutoSliding() {
        val delay = 8000L // Delay in milliseconds between sliding
        val period = 8000L // Period in milliseconds for automatic sliding

        timer?.schedule(delay, period) {
            lifecycleScope.launch(Dispatchers.Main) {
                val currentPosition = binding.homeSlider.currentItem
                val nextPosition =
                    if (currentPosition < homeSliderAdapter.itemCount - 1) currentPosition + 1 else 0
                binding.homeSlider.setCurrentItem(nextPosition, true)
            }
        }
    }

    private fun stopAutoSliding() {
        timer?.cancel()
        timer = null
    }

    private fun onClick() {
        binding.homeProfilePic.setOnClickListener {
            startActivity(Intent(this@HomeActivity, AboutActivity::class.java))
        }

        binding.homeSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorsPosition = position
                changeSliderIndicator(indicatorsPosition)
            }
        })

        homeSliderAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }

        ottAdapter.onItemClick = {
            redirectToTagActivity(it)
        }

        top10Adapter.onItemClick = {
            redirectToMovieActivity(it)
        }

        topRatedAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }

        languagesAdapter.onItemClick = {
            redirectToTagActivity(it)
        }

        popularMoviesAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }

        popularSeriesAdapter.onItemClick = {
            redirectToMovieActivity(it)
        }
    }

    private fun redirectToMovieActivity(movie: Movie){
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra("MOVIE", movie)
        startActivity(intent)
        inAnimation()
    }

    private fun redirectToTagActivity(tag: Tag){
        val intent = Intent(this, TagActivity::class.java)
        intent.putExtra("TAG", tag.name)
        startActivity(intent)
        inAnimation()
    }

    private fun inAnimation() {
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoSliding()
    }

}


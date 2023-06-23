package com.one.vision

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var previousScrollY = 0

    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    private var sliderList = ArrayList<Movie>()
    private var top10List = ArrayList<Movie>()
    private var ottList = ArrayList<Int>()
    private var topRatedList = ArrayList<Movie>()
    private var languagesList = ArrayList<Int>()
    private var popularMoviesList = ArrayList<Movie>()
    private var popularSeriesList = ArrayList<Movie>()

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
        setIndicators()
        getOttData()
        getTop10Data()
        getTopRatedData()
        getLanguagesData()
        getPopularMoviesData()
        getPopularSeriesData()
        startAutoSliding()
        onClick()

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

    private fun init() {
        homeSliderAdapter = HomeSliderAdapter()
        top10Adapter = Top10Adapter()
        ottAdapter = OttAdapter()
        topRatedAdapter = TopRatedAdapter()
        languagesAdapter = LanguagesAdapter()
        popularMoviesAdapter = PopularMoviesAdapter()
        popularSeriesAdapter = PopularSeriesAdapter()
        customItemMargin = CustomItemMargin()
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

    private fun getSliderData() {
        sliderList.apply {
            add(
                Movie(
                    "https://m.media-amazon.com/images/I/91A9U++FKnL._AC_SL1500_.jpg",
                    "Aftermath",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://wallpapercave.com/wp/wp8215948.jpg",
                    "Black Adam",
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/1f/fa/80/1ffa80d041d5bc2171d8f783e4e26f0d.jpg",
                    "Dawn Of The Planets Of Apes",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/02/b0/a8/02b0a85a3f985f02efec365b410bf52e.jpg",
                    "Emancipation",
                    null,
                    false
                )
            )
        }
        setSliderData()
    }

    private fun setSliderData() {
        homeSliderAdapter.setMoviesList(this, sliderList)
        binding.homeSlider.adapter = homeSliderAdapter
    }

    private fun getOttData() {
        ottList.apply {
            add(R.drawable.ic_disney_hotstar)
            add(R.drawable.ic_primevideo)
            add(R.drawable.ic_netflix)
            add(R.drawable.ic_zee5)
            add(R.drawable.ic_altbalaji)
            add(R.drawable.ic_voot)
            add(R.drawable.ic_jiocinema)
            add(R.drawable.ic_sonyliv)
        }
        setOttData()
    }

    private fun setOttData() {
        ottAdapter.setOttList(this, ottList)
        binding.ottRv.apply {
            addItemDecoration(customItemMargin)
            adapter = ottAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getTop10Data() {
        top10List.apply {
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
        setTop10List()
    }

    private fun setTop10List() {
        top10Adapter.setMoviesList(this, top10List)
        binding.top10Rv.apply {
            addItemDecoration(customItemMargin)
            adapter = top10Adapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setIndicators() {
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
        changeIndicator(indicatorsPosition)
    }

    private fun changeIndicator(position: Int) {
        for (i in indicators.indices) {
            if (i == position) {
                indicators[i]?.setImageDrawable(getDrawable(R.drawable.home_slider_active_indicator))
            } else {
                indicators[i]?.setImageDrawable(getDrawable(R.drawable.home_slider_inactive_indicator))
            }
        }
    }

    private fun getTopRatedData() {
        topRatedList.apply {
            add(
                Movie(
                    "https://i.pinimg.com/564x/7c/49/96/7c49969006a906eea4692ad8026c96af.jpg",
                    null,
                    "4.9",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/06/6f/6a/066f6ac7c266bc906ae1b748bec9a5e5.jpg",
                    null,
                    "4.6",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/60/cc/4b/60cc4b1f4ffb59d1e7477e11066f2667.jpg",
                    null,
                    "4.5",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/04/e1/4f/04e14f9b5ee33614c9d118f58c075621.jpg",
                    null,
                    "4.4",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/ba/90/db/ba90db018e49aacb34df10299bafa681.jpg",
                    null,
                    "4.1",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/01/60/ed/0160edbca7e85a217180bebd8a146725.jpg",
                    null,
                    "4.0",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/17/85/ff/1785ff6b63efbd0ecd12d9ef1f77665c.jpg",
                    null,
                    "3.9",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/81/be/0e/81be0eaa11da58bc5623ef17ced6d3bc.jpg",
                    null,
                    "3.8",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/e1/4b/0b/e14b0b20f95022ac0bec6ed35733ca94.jpg",
                    null,
                    "3.7",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/41/4a/69/414a69b0cbf4eb05357fd8b13209bf66.jpg",
                    null,
                    "3.6",
                    false
                )
            )
        }
        setTopRatedList()
    }

    private fun setTopRatedList() {
        topRatedAdapter.setMoviesList(this, topRatedList)
        binding.topRatedRv.apply {
            addItemDecoration(customItemMargin)
            adapter = topRatedAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getLanguagesData() {
        languagesList.apply {
            add(R.drawable.languages_hindi)
            add(R.drawable.languages_marathi)
            add(R.drawable.languages_english)
        }
        setLanguagesData()
    }

    private fun setLanguagesData() {
        languagesAdapter.setLanguagesList(this, languagesList)
        binding.languagesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = languagesAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getPopularMoviesData() {
        popularMoviesList.apply {
            add(
                Movie(
                    "https://i.pinimg.com/564x/ea/6e/ec/ea6eecb7ea4e5095b5e3fff131af005d.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/69/43/9b/69439bb8907bc736b62753988e80d5c0.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/d1/29/86/d12986bc3cb9bfb647e364fab67afe8f.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/f3/b5/71/f3b5719f74b8dc6106bc83f862ac199e.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/80/99/d7/8099d73a30203baf03709c5e75627e76.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/6f/cd/87/6fcd873a7bcbb157d5d5a6fc3869b656.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/5c/88/0e/5c880e187f00e2309726172c53412400.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/13/4f/9f/134f9fbf72fc869faf5bd4894b96fa42.jpg",
                    null,
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/d0/cf/7e/d0cf7e201ae8a9b06fd736a577d85894.jpg",
                    null,
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/736x/97/94/15/979415cd34b9c25fe1e4d6c4a070ef3f.jpg",
                    null,
                    null,
                    false
                )
            )
        }
        setPopularMoviesList()
    }

    private fun setPopularMoviesList() {
        popularMoviesAdapter.setMoviesList(this, popularMoviesList)
        binding.popularMoviesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = popularMoviesAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getPopularSeriesData() {
        popularSeriesList.apply {
            add(
                Movie(
                    "https://i.pinimg.com/564x/7f/fe/c8/7ffec8272ccaf6ca6191de91c1af5daf.jpg",
                    null,
                    "4.9",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/9b/15/18/9b15186d66b4d0a98f08de722bc39521.jpg",
                    null,
                    "4.6",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/f7/74/db/f774dbf5a2d6e5352c80eee32364aaab.jpg",
                    null,
                    "4.5",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/10/76/a8/1076a86eb2dcad3d22350e0aeb8e847d.jpg",
                    null,
                    "4.4",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/c1/f7/bf/c1f7bfc1e35aa4fd364ed7c98418b294.jpg",
                    null,
                    "4.1",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/51/3b/e4/513be4225f05ac71031514abd9b16a53.jpg",
                    null,
                    "4.0",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/b5/45/f9/b545f9a1ff2035d3fdf18f38393bd1ac.jpg",
                    null,
                    "3.9",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/6d/fa/e4/6dfae40e409696e3eb1c81bd3f60f507.jpg",
                    null,
                    "3.8",
                    false
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/b3/ad/cc/b3adcc57618acfe2f06233c8fb73bf25.jpg",
                    null,
                    "3.7",
                    true
                )
            )
            add(
                Movie(
                    "https://i.pinimg.com/564x/97/c0/b3/97c0b3c7889aa8b13d236341e2d9bb64.jpg",
                    null,
                    "3.6",
                    false
                )
            )
        }
        setPopularSeriesList()
    }

    private fun setPopularSeriesList() {
        popularSeriesAdapter.setMoviesList(this, popularSeriesList)
        binding.popularSeriesRv.apply {
            addItemDecoration(customItemMargin)
            adapter = popularSeriesAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun startAutoSliding() {
        val delay = 8000L // Delay in milliseconds between sliding
        val period = 8000L // Period in milliseconds for automatic sliding

        timer?.schedule(delay, period) {
            lifecycleScope.launch(Dispatchers.Main) {
                val currentPosition = binding.homeSlider.currentItem
                val nextPosition = if (currentPosition < homeSliderAdapter.itemCount - 1) currentPosition + 1 else 0
                binding.homeSlider.setCurrentItem(nextPosition, true)
            }
        }
    }

    private fun stopAutoSliding() {
        timer?.cancel()
        timer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoSliding()
    }

    private fun onClick() {
        binding.homeProfilePic.setOnClickListener {
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left)
        }

        binding.homeSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorsPosition = position
                changeIndicator(indicatorsPosition)
            }
        })
    }

}


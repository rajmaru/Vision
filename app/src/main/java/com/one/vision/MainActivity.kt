package com.one.vision

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.one.vision.adapters.HomeSliderAdapter
import com.one.vision.adapters.OttAdapter
import com.one.vision.adapters.Top10Adapter
import com.one.vision.databinding.ActivityMainBinding
import com.one.vision.itemdecoration.CustomItemMargin
import com.one.vision.models.Movie
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    private var sliderList = ArrayList<Movie>()
    private var top10List = ArrayList<Movie>()
    private var ottList = ArrayList<Int>()

    private lateinit var homeSliderAdapter: HomeSliderAdapter
    private lateinit var top10Adapter: Top10Adapter
    private lateinit var ottAdapter: OttAdapter

    private lateinit var customItemMargin: CustomItemMargin

    private var indicators = arrayOfNulls<ImageView>(0)
    private var indicatorsPosition = 0
    private var timer: Timer? = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getUserFromFirebase()
        getSliderData()
        getTop10Data()
        getOttData()
        setIndicators()
        startAutoSliding()
        onClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoSliding()
    }

    private fun init() {
        homeSliderAdapter = HomeSliderAdapter()
        top10Adapter = Top10Adapter()
        ottAdapter = OttAdapter()
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
            add(Movie("https://m.media-amazon.com/images/I/51BANINoAxL._AC_.jpg", "Drive", true))
            add(
                Movie(
                    "https://mb.cision.com/Public/14247/2902071/856b720fb81856ec_800x800ar.jpg",
                    "WAR",
                    false
                )
            )
            add(Movie("https://i.ebayimg.com/images/g/GtEAAOSw1W9eN1cY/s-l1600.jpg", "1947", true))
            add(Movie("https://img.artpal.com/610421/5-56-24t.jpg", "Reversion", false))
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
                    "https://marketplace.canva.com/EAFH3gODxw4/1/0/1131w/canva-black-%26-white-modern-mystery-forest-movie-poster-rLty9dwhGG4.jpg",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/action-movie-poster-template-design-0f5fff6262fdefb855e3a9a3f0fdd361_screen.jpg?ts=1636996054",
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://assets-global.website-files.com/6009ec8cda7f305645c9d91b/6408f6e7b5811271dc883aa8_batman-min.png",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTARVsOtOpjwGUh1Z-n-t7Ggu2sDreOsr11Gg&usqp=CAU",
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://marketplace.canva.com/EAFH3gODxw4/1/0/1131w/canva-black-%26-white-modern-mystery-forest-movie-poster-rLty9dwhGG4.jpg",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/action-movie-poster-template-design-0f5fff6262fdefb855e3a9a3f0fdd361_screen.jpg?ts=1636996054",
                    null,
                    false
                )
            )
            add(
                Movie(
                    "https://assets-global.website-files.com/6009ec8cda7f305645c9d91b/6408f6e7b5811271dc883aa8_batman-min.png",
                    null,
                    true
                )
            )
            add(
                Movie(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTARVsOtOpjwGUh1Z-n-t7Ggu2sDreOsr11Gg&usqp=CAU",
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

    private fun startAutoSliding() {
        val delay = 3000L // Delay in milliseconds between sliding
        val period = 5000L // Period in milliseconds for automatic sliding

        timer?.schedule(delay, period) {
            runOnUiThread {
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
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
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


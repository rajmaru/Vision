package com.one.vision

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.one.vision.adapters.HomeSliderAdapter
import com.one.vision.databinding.ActivityMainBinding
import com.one.vision.models.Movie
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var moviesList = ArrayList<Movie>()
    private lateinit var homeSliderAdapter: HomeSliderAdapter
    private var indicators= arrayOfNulls<ImageView>(0)
    private var indicatorsPosition = 0
    private var timer: Timer? = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getUserFromFirebase()
        getSliderData()
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
        moviesList.apply {
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
        homeSliderAdapter.setMoviesList(this, moviesList)
        binding.homeSlider.adapter = homeSliderAdapter
    }

    private fun setIndicators() {
        val indicatorsCount = binding.homeSlider.adapter?.itemCount ?: 0
        indicators = arrayOfNulls(indicatorsCount)

        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(10, 0, 10, 0)

        for (i in 0 until indicatorsCount) {
            indicators[i] = ImageView(this)
            indicators[i]?.layoutParams = layoutParams
            binding.homeSliderDotsLayout.addView(indicators[i])
        }
        changeIndicator(indicatorsPosition)
    }

    private fun changeIndicator(position: Int){
        for (i in indicators.indices) {
            if(i == position){
                Log.d("RAJ",position.toString())
                indicators[i]?.setImageDrawable(getDrawable(R.drawable.home_slider_active_indicator))
            }else{
                Log.d("RAJ",position.toString())
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
                val nextPosition = if (currentPosition < homeSliderAdapter.itemCount - 1) currentPosition + 1 else 0
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


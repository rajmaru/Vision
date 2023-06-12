package com.one.vision

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.one.vision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getUserFromFirebase()
        setProfilePic()
        onClick()
    }

    private fun init(){

    }

    private fun onClick() {
        binding.homeProfilePic.setOnClickListener{
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }
    }

    private fun getUserFromFirebase() {
        auth = Firebase.auth
        user = auth!!.currentUser
    }

    private fun setProfilePic() {
        if (user != null) {
            Glide.with(this)
                .load(user!!.photoUrl)
                .into(binding.homeProfilePic)
        }
    }
}


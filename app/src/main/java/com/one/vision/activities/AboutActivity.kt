package com.one.vision.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.one.vision.R
import com.one.vision.databinding.ActivityAboutBinding
import com.one.vision.fragments.PricingBottomSheet

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
        getUserFromFirebase()
        setUserDataInViews()

    }

    private fun init(){
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun onClick(){
        binding.aboutLogout.setOnClickListener {
            logOut()
        }

        binding.aboutBackButton.setOnClickListener{
            onBackPressed()
        }

        binding.aboutPlansBtn.setOnClickListener {
            val pricingBottomSheet = PricingBottomSheet()
            pricingBottomSheet.show(supportFragmentManager, "PRICING_BOTTOMSHEET")
        }
    }

    private fun getUserFromFirebase() {
        val auth = Firebase.auth
        user = auth.currentUser
    }

    private fun setUserDataInViews() {
        if (user != null) {
            Glide.with(this)
                .load(user!!.photoUrl)
                .into(binding.aboutProfilePic)
            binding.aboutNameTv.text = user!!.displayName
            binding.aboutEmailTv.text = user!!.email
        }
    }

    private fun logOut() {
        mAuth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            val intent = Intent(this@AboutActivity, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}
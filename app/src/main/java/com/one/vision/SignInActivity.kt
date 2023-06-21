package com.one.vision

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.one.vision.adapters.OttAdapter
import com.one.vision.adapters.SigninOttAdapter
import com.one.vision.databinding.ActivitySignInBinding
import com.one.vision.itemdecoration.CustomItemMargin

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var ottAdapter: SigninOttAdapter
    private var ottList = ArrayList<Int>()
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                handleSignInResult(intent)
            } else {
                Log.d("SIGN_IN", "Google sign in failed")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        init()
        getOttData()
        setSliderData()
        checkCurrentUser()
        onClick()
    }

    private fun setSliderData() {
        Glide.with(this)
            .load("https://i.pinimg.com/564x/60/cc/4b/60cc4b1f4ffb59d1e7477e11066f2667.jpg")
            .into(binding.signinSliderImg1)

        Glide.with(this)
            .load("https://i.pinimg.com/564x/7e/ce/1d/7ece1d82b0da68f18552688231759249.jpg")
            .into(binding.signinSliderImg2)

        Glide.with(this)
            .load("https://i.pinimg.com/564x/02/12/b4/0212b4a89dc44f6cf134af4a4d13f156.jpg")
            .into(binding.signinSliderImg3)

        Glide.with(this)
            .load("https://i.pinimg.com/564x/7f/fe/c8/7ffec8272ccaf6ca6191de91c1af5daf.jpg")
            .into(binding.signinSliderImg4)
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        ottAdapter = SigninOttAdapter()
    }

    private fun getOttData() {
        ottList.apply {
            add(R.drawable.ic_jiocinema)
            add(R.drawable.ic_disney_hotstar)
            add(R.drawable.ic_voot)
            add(R.drawable.ic_primevideo)
            add(R.drawable.ic_netflix)
            add(R.drawable.ic_zee5)
            add(R.drawable.ic_altbalaji)
            add(R.drawable.ic_sonyliv)
        }
        setOttData()
    }

    private fun setOttData() {
        ottAdapter.setOttList(this, ottList)
        binding.signinOttRv.apply {
            adapter = ottAdapter
            layoutManager =
                GridLayoutManager(this@SignInActivity, 4)
        }
    }

    private fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun onClick() {
        binding.signinButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(intent: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.d("SIGN_IN", "Google sign in failed: ${e.message}")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.d("SIGN_IN", "Authentication failed")
                }
            }
    }
}

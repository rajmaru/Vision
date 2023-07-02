package com.one.vision.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout.LayoutParams
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.one.vision.R
import com.one.vision.databinding.ActivityVideoViewBinding

class VideoViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoViewBinding
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.videoPlayer.player = exoPlayer
        val mediaItem = MediaItem.fromUri("https://t.me/vision888888/2")
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun onResume() {
        super.onResume()
        exoPlayer.playWhenReady = true
        exoPlayer.play()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
        exoPlayer.playWhenReady = false
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.pause()
        exoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
    }
}
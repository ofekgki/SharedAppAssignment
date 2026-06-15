package com.example.common

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

// Extension function to easily play a sound from any Context (like an Activity)
fun Context.playSound(@RawRes soundResId: Int) {
    val mediaPlayer: MediaPlayer? = MediaPlayer.create(this, soundResId)
    mediaPlayer?.setOnCompletionListener {
        it.release()
    }
    mediaPlayer?.start()
}

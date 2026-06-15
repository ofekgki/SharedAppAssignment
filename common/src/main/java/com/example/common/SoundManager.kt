package com.example.common

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.annotation.RawRes

object SoundManager {
    private var soundPool: SoundPool? = null
    private val soundMap = mutableMapOf<Int, Int>()

    fun init(context: Context) {
        if (soundPool == null) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            soundPool = SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build()

            soundMap[R.raw.sound_action] = soundPool!!.load(context, R.raw.sound_action, 1)
            soundMap[R.raw.sound_fail] = soundPool!!.load(context, R.raw.sound_fail, 1)
        }
    }

    fun playSound(@RawRes soundResId: Int) {
        val soundId = soundMap[soundResId]
        if (soundId != null) {
            soundPool?.play(soundId, 1f, 1f, 1, 0, 1f)
        }
    }
}

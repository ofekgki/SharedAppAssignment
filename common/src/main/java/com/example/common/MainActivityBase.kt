package com.example.common

import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.common.databinding.ActivityMainBaseBinding


open class MainActivityBase : AppCompatActivity() {
    private lateinit var binding: ActivityMainBaseBinding

    private var actionPlaced = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBaseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MainActivity_Base)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SoundManager.init(this)

        findViewById<Button>(R.id.BTN_action).setOnClickListener {
            if (!actionPlaced) {
                onActionClicked()
            } else {
                Toast.makeText(this, getString(R.string.action_clicked_already), Toast.LENGTH_SHORT).show()
                SoundManager.playSound(R.raw.sound_fail)
            }
        }
    }

    open fun onActionClicked() {
        binding.LBLStatus.text = getString(R.string.LBL_status)
        binding.LBLStatusTitle.text = getString(R.string.LBL_statusTitle)
        Toast.makeText(this, getString(R.string.action_clicked), Toast.LENGTH_SHORT).show()
        SoundManager.playSound(R.raw.sound_action)

        actionPlaced = true

    }
}

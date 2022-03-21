package com.jts.agrodei.acitivties.init

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jts.agrodei.databinding.ActivityInitBinding
import com.jts.agrodei.utils.Utility

class Init : AppCompatActivity() {
    private lateinit var binding: ActivityInitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appCaption.text = Utility.getAppName(applicationContext)+"\n"
    }
}
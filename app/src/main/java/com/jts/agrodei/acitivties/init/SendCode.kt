package com.jts.agrodei.acitivties.init

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jts.agrodei.databinding.ActivitySendCodeBinding

class SendCode : AppCompatActivity() {
    private lateinit var binding: ActivitySendCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
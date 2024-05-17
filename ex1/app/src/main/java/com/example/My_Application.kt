package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding


class My_Application : AppCompatActivity() {

    private var fronsizee = 10f
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.newbutton.setOnClickListener{
            fronsizee+=4f
            binding.newone.setTextSize(TypedValue.COMPLEX_UNIT_SP,fronsizee)
        }
        binding.button2.setOnClickListener{
//            fronsizee=30f
            binding.newone.setTextColor(Color.RED)
//            binding.newone.setTextColor(Color.BLACK)
//            binding.layout.setBackgroundColor(Color.YELLOW)
//            binding.newone.setTextSize(TypedValue.COMPLEX_UNIT_SP,fronsizee)
        }
        binding.button3.setOnClickListener{
            binding.layout.setBackgroundColor(Color.GREEN)
        }
    }
}
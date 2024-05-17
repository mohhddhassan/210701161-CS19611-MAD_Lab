package com.example.app8

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.app8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlert.setOnClickListener {
            val alert_message = binding.alertMessage.text.toString()

            alert(alert_message)
        }

    }

    private fun alert(alert_message : String) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(alert_message)
            .setTitle("Alert")
            .setCancelable(true)
            .setPositiveButton("OK") {_, _->
                Toast.makeText(applicationContext, "You clicked 'OK'", Toast.LENGTH_SHORT).show()
                binding.alertMessage.text.clear()
            }
            .setNegativeButton("Cancel") {_, _->
                Toast.makeText(applicationContext, "You clicked 'CANCEL'", Toast.LENGTH_SHORT).show()
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
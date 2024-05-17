package com.example.app6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app6.databinding.ActivityMainBinding
import com.example.app6.ui.theme.App6Theme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnValidate.setOnClickListener {
            validate()
        }
    }

    private fun validate ()
    {
        val username = binding.username.text.toString()
        val pattern = "[^A-Za-z]".toRegex()

        val isInvalidUserName = pattern.containsMatchIn(username)
        if (isInvalidUserName || username.length == 0)
            Toast.makeText(applicationContext, "Enter only alphabets username", Toast.LENGTH_SHORT).show()

        val pin = binding.pin.text.toString()

        val isInvalidPin = if (pin.length < 4) true else false
        if (isInvalidPin)
            Toast.makeText(applicationContext, "Enter 4 digit pin", Toast.LENGTH_SHORT).show()

        if (!isInvalidUserName && !isInvalidPin)
            Toast.makeText(applicationContext, "Valid username & pin", Toast.LENGTH_SHORT).show()
    }
}
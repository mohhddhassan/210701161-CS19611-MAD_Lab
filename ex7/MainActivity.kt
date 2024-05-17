package com.example.app7

import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app7.databinding.ActivityMainBinding
import com.example.app7.ui.theme.App7Theme
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(check_sd_mounted() == true) {

            binding.btnSave.setOnClickListener {
                val register_no = binding.rollNo.text.toString()
                val name = binding.name.text.toString()
                val cgpa = binding.cgpa.text.toString()
                var file_name = binding.fileName.text.toString()
                if (file_name == "")
                    file_name = "sample.txt"

                saveFile(register_no, name, cgpa, file_name)
            }

            binding.btnLoad.setOnClickListener {
                var file_name = binding.fileName.text.toString()
                if (file_name == "")
                    file_name = "sample.txt"
                loadFile(file_name)
            }
        }

        else {
            binding.btnSave.isEnabled = false
            binding.btnSave.setBackgroundColor(Color.parseColor("#CCCCCC"))
            binding.btnLoad.isEnabled = false
            binding.btnLoad.setBackgroundColor(Color.parseColor("#808080"))
        }
    }

    private fun check_sd_mounted() : Boolean {
        val is_mounted = Environment.getExternalStorageState()
        if (is_mounted == Environment.MEDIA_MOUNTED)
            return true
        return false
    }

    private fun saveFile(register_no : String, name : String, cgpa : String, file_name : String) {

        val file = File(getExternalFilesDir(null), file_name)
        val fos = FileOutputStream(file)
        fos.write ("Name : $register_no \nName : $name \nCGPA : $cgpa".toByteArray())
        fos.close()

        Toast.makeText(applicationContext, "Text file saved successfully", Toast.LENGTH_SHORT).show()
        binding.rollNo.text.clear()
        binding.name.text.clear()
        binding.cgpa.text.clear()
        binding.fileName.text.clear()
        binding.loadTv.text = ""
    }

    private fun loadFile(file_name: String) {

        val file = File(getExternalFilesDir(null), file_name)
        val fis = FileInputStream(file)
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)

        val data : List<String> = br.readLines()
        val write = data.joinToString("\n")
        binding.loadTv.text = write
        fis.close()

        Toast.makeText(applicationContext, "Text file loaded successfully", Toast.LENGTH_SHORT).show()
        binding.fileName.text.clear()
    }
}